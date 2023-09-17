package com.great.common.web;

/**
 * Created on 2021/8/27 14:01
 * 定义成功的状态和系统问题导致的状态 以5开头
 *
 * @author Y.X
 */
public enum SysStatus implements StatusResult {
    /**
     * 对于所有的未知的异常返回该值
     */
    SYS_ERR(50000, "处理失败"),
    /**
     * 系统繁忙异常，适用于需要重试的操作，主要提供给用户
     */
    SYS_BUSY(51000, "系统繁忙"),
    /**
     * 系统间调用时出现超时，或者连接失败时，主要存在于系统直接的调用
     */
    SYS_CALL_ERR(51001, "服务繁忙"),
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

    SysStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
