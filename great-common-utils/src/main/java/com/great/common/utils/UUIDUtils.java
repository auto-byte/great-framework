package com.great.common.utils;

import java.util.UUID;

/**
 * 序列工具
 *
 * @author Y.X
 * @since 2021/9/9
 */
public abstract class UUIDUtils {

    /**
     * UUID生成
     *
     * @param toUpperCase true 表示转换成大写
     */
    public static String uuid(boolean toUpperCase) {
        String uuid = UUID.randomUUID().toString();
        return toUpperCase ? uuid.toUpperCase() : uuid;
    }

    /**
     * UUID生成, 全大写
     */
    public static String uuid() {
        return uuid(false);
    }

    /**
     * UUID生成, 去除 '-'
     */
    public static String uuidAlp() {
        return uuid(false).replaceAll("-", "");
    }

    /**
     * UUID生成, 去除 '-'
     */
    public static String uuidAlpUp() {
        return uuid(true).replaceAll("-", "");
    }

    /**
     * UUID生成, 取长度8
     */
    public static String uuidAlp8() {
        return uuidAlp().substring(0, 8);
    }

    /**
     * UUID生成, 取长度8, 先截取长度，然后转大写，性能提升
     */
    public static String uuidAlp8Up() {
        return uuidAlp().substring(0, 8).toUpperCase();
    }

    /**
     * UUID生成, 取长度12
     */
    public static String uuidAlp12() {
        return uuidAlp().substring(0, 12);
    }

    /**
     * UUID生成, 取长度12，转大写
     */
    public static String uuidAlp12Up() {
        return uuidAlp().substring(0, 12).toUpperCase();
    }
}
