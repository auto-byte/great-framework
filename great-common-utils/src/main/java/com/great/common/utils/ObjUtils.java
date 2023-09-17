package com.great.common.utils;

/**
 * Created on 2021/9/27 17:07
 *
 * @author Y.X
 */
public abstract class ObjUtils {

    public static <T> T get(T obj, T def) {
        return obj != null ? obj : def;
    }
}
