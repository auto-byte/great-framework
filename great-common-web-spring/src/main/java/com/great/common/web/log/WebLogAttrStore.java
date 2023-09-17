package com.great.common.web.log;

/**
 * Created on 2021/12/9 11:32
 *
 * @author Y.X
 */
public class WebLogAttrStore {

    private static final ThreadLocal<WebLogAttr> logAttrTL = new ThreadLocal<>();

    public static void store(WebLogAttr webLogAttr) {
        logAttrTL.set(webLogAttr);
    }

    public static WebLogAttr getAttr() {
        return logAttrTL.get();
    }

    public static void remove() {
        logAttrTL.remove();
    }
}
