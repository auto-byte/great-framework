package com.great.common.web.log;

import org.aspectj.lang.JoinPoint;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2021/11/23 12:29
 *
 * @author Y.X
 */
public class DefaultAspectLogCollector extends AbstractWebLogCollector implements WebLogCollector<WebLogAttr> {

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 收集必要的日志信息
     *
     * @param point 切点
     * @return 日志信息
     */
    @Override
    public WebLogAttr collect(JoinPoint point) {
        WebLogAttr apiLogAttr = new WebLogAttr();
        apiLogAttr.setPoint(point);
        collectParams(point, apiLogAttr);
        apiLogAttr.setClassName(point.getTarget().getClass().getName());
        apiLogAttr.setMethod(point.getSignature().getName());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            apiLogAttr.setRequest(request);
            apiLogAttr.setHttpMethod(request.getMethod());
            apiLogAttr.setUrl(request.getRequestURI());
            try {
                apiLogAttr.setIp(getIpAddr(request));
            } catch (Exception e) {
                apiLogAttr.setIp("unknown");
            }
        }

        return apiLogAttr;
    }
}
