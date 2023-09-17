package com.great.common.web;

/**
 * Created on 2021/11/26 09:39
 *
 * @author Y.X
 */
public abstract class RegCons {
    /**
     * 用户名正则，4到16位（字母，数字，下划线，减号）
     */
    public static final String USERNAME = "^[a-zA-Z][a-zA-Z0-9_-]{3,15}$";
    /**
     * 强密码
     */
    public static final String STRONG_PWD = "^.*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[`~!@#$%^&*()+=|\\\\{}\\[\\]':;\"<>,./?]).*$";
    /**
     * 弱密码
     */
    public static final String WEAK_PASSWD = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|\\\\{}\\[\\]':;\"<>,./?]){6,32}$";
    /**
     * 邮箱
     */
    public static final String EMAIL = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
    /**
     * 手机号（大陆）
     */
    public static final String PHONE_ZH = "^(13[0-9]|14[01456879]|15[0-3,5-9]|16[2567]|17[0-8]|18[0-9]|19[0-9])\\d{8}$";
    /**
     * 身份证
     */
    public static final String ID_CARD = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
}
