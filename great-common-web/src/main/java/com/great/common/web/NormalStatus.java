package com.great.common.web;

/**
 * Created on 2021/9/8 16:24
 *
 * @author Y.X
 */
public enum NormalStatus implements StatusResult {
    /**
     * 对于所有的操作成功返回该值
     */
    SUCCESS(0, "操作成功"),
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

    NormalStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
