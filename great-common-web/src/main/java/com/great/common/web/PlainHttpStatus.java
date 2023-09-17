package com.great.common.web;

/**
 * Http请求状态
 *
 * @author Y.X
 * @since 2021/9/7
 */
public enum PlainHttpStatus implements StatusResult {
    OK(200, "OK"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "认证未通过"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403, "您无法访问"),
    NOT_FOUND(404, "资源未找到"),
    METHOD_NOT_ALLOWED(405, "请求方式不正确"),
    NOT_ACCEPTABLE(406, "不接收"),
    INTERNAL_SERVER_ERROR(500, "系统好像出了点问题"),
    NOT_IMPLEMENTED(501, "未实现"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "服务超时"),
    ;

    private final int code;

    private final String message;

    PlainHttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
