package com.great.common.web.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Y.X
 * @since 2021/9/11
 */
public abstract class AbstractWebLogCollector {

    private static final Logger log = LoggerFactory.getLogger(AbstractWebLogCollector.class);

    /**
     * 处理方法中的参数
     */
    public static void collectParams(JoinPoint point, WebLogAttr apiLogAttr) {
        String[] argNames = ((MethodSignature) point.getSignature()).getParameterNames();
        Object[] args = point.getArgs();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < argNames.length; ++i) {
            Object arg = args[i];
            if (!(arg instanceof ServletResponse) && !(arg instanceof ServletRequest)) {
                if (arg instanceof MultipartFile) {
                    apiLogAttr.addParam(argNames[i], "MultipartFile");
                }
                apiLogAttr.addParam(argNames[i], arg);
            }
        }
    }
}
