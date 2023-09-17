package com.great.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Y.X
 * @since 2021/9/16
 */
public abstract class Jackson {
    public static final ObjectMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(Jackson.class);

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());
    }

    /**
     * 对象转JSON字符串
     *
     * @param object 对象
     * @return json
     */
    public static String toJsonString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warn("obj: {} to str error.", object, e);
            return "";
        }
    }

    /**
     * 字符串转对象
     *
     * @param jsonStr 字符串
     * @param clazz   对象class
     * @param <T>     对象类型
     * @return 实体
     */
    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            log.warn("{} to {} error.", jsonStr, clazz.getName(), e);
            return null;
        }
    }

    /**
     * 字符串转对象
     *
     * @param jsonStr 字符串
     * @param type    对象类型
     * @param <T>     对象范型
     * @return 实体
     */
    public static <T> T toObject(String jsonStr, TypeReference<T> type) {
        try {
            return mapper.readValue(jsonStr, type);
        } catch (JsonProcessingException e) {
            log.warn("{} to {} error.", jsonStr, type.getType().getTypeName(), e);
            return null;
        }
    }
}
