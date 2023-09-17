package com.great.common.web.log;

import com.great.common.web.StatusException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created on 2021/11/23 13:13
 *
 * @author Y.X
 */
public class DefaultAspectLogListener implements WebLogListener {

    private static final Logger log = LoggerFactory.getLogger("com.great.web.log.AspectLog");

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());
    }

    @Resource
    private WebLogProps skaperApiProps;

    public static String toStr(Object obj) {
        return toStr(obj, -1);
    }

    /**
     * 将对象转换成参数
     *
     * @param obj       对象
     * @param maxLength 最大长度，防止有些接口返回值太长
     * @return 字符串
     */
    public static String toStr(Object obj, int maxLength) {
        try {
            String json = objectMapper.writeValueAsString(obj);
            if (json != null && maxLength > 0 && json.length() > maxLength) {
                return json.substring(0, maxLength);
            }
            return json;
        } catch (JsonProcessingException e) {
            log.warn("parse obj: {} to string fail.", obj);
            return "";
        }
    }

    @Override
    public void start(WebLogAttr webLogAttr) {
        String httpMethod = webLogAttr.getHttpMethod();
        String id = webLogAttr.getShortRequestId();
        String className = webLogAttr.shortClassName();
        String methodName = webLogAttr.getMethod();
        String params = toStr(webLogAttr.getParams());
        String url = webLogAttr.getUrl();
        String paramsFormat = params == null ? "" : " => " + params;

        if (skaperApiProps.getLog().getShowIp()) {
            String ip = webLogAttr.getIp();
            log.info("Start -> ID: {}, IP: {}, {} {}, {}.{}() {}",
                    id, ip, httpMethod, url, className, methodName, paramsFormat);
        } else {
            log.info("Start -> ID: {}, {} {}, {}.{}() {}",
                    id, httpMethod, url, className, methodName, paramsFormat);
        }
    }

    @Override
    public void end(WebLogAttr webLogAttr) {
        Throwable throwable = webLogAttr.getException();
        String id = webLogAttr.getShortRequestId();
        String resStr = toStr(webLogAttr.getResult(), skaperApiProps.getLog().getMaxLength());
        long cost = webLogAttr.getCost();

        if (throwable == null) {
            log.info("End   -> ID: {}, COST {}ms => {} ", id, cost, resStr);
        } else if (throwable instanceof StatusException) {
            log.warn("End   -> ID: {}, COST {}ms => {}", id, cost, resStr);
        } else {
            log.error("End   -> ID: {}, COST {}ms => {}", id, cost, resStr, throwable);
        }
    }
}
