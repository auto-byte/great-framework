package com.great.common.web;

/**
 * Created on 2021/8/27 14:32
 * 定义业务异常 以 3开头
 *
 * @author Y.X
 */
public enum BizStatus implements StatusResult {

    OPT_FAIL(30000, "操作失败"),

    NOT_FOUND(30010, "记录不存在"),
    ;

    private final int code;

    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    BizStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
