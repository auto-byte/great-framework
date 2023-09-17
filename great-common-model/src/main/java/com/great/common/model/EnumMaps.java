package com.great.common.model;


import com.great.common.model.enums.KeyEnum;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created on 2021/9/17 19:18
 *
 * @author Y.X
 */
public abstract class EnumMaps {
    /**
     * 将举转换成 Key:V 的 Map
     *
     * @param enumClass 枚举类
     * @param <T>       枚举类型
     * @return Map<Integer, T> 不可变
     */
    public static <K, T extends Enum<T> & KeyEnum<K>> Map<K, T> keyMap(final Class<T> enumClass) {
        return map(enumClass, v -> v.getKey());
    }


    /**
     * 将枚举类型转换成 Key: KeyFunc 的 Map
     *
     * @param enumClass 枚举类
     * @param keyFunc   Key生成方式
     * @param <T>       枚举类型
     * @param <R>       Key类型
     * @return Map<R, T> 不可变
     */
    public static <T extends Enum<T>, R> Map<R, T> map(final Class<T> enumClass, Function<T, R> keyFunc) {
        EnumSet<T> items = EnumSet.allOf(enumClass);
        Map<R, T> enumMap = new HashMap<>(items.size(), 1);
        for (T item : EnumSet.allOf(enumClass)) {
            enumMap.put(keyFunc.apply(item), item);
        }
        return Collections.unmodifiableMap(enumMap);
    }
}
