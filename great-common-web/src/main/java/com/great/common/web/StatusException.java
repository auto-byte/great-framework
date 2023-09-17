package com.great.common.web;

/**
 * 基本的可获取异常状态code的自定义异常，
 * 如果有业务类型的异常可以通过继承该类型实现
 * <p>
 * Created on 2021/9/8 14:33
 *
 * @author Y.X
 */
public class StatusException extends RuntimeException {
    /**
     * 业务的状态
     */
    protected final int code;

    public StatusException(int code, String message) {
        super(message);
        this.code = code;
    }

    public StatusException(String message) {
        super(message);
        this.code = PlainHttpStatus.INTERNAL_SERVER_ERROR.getCode();
    }

    public StatusException(StatusResult status) {
        super(status.getMessage());
        this.code = status.getCode();
    }

    public StatusException(StatusResult status, String message) {
        super(message);
        this.code = status.getCode();
    }

    public StatusException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public StatusException(StatusResult status, Throwable cause) {
        super(status.getMessage(), cause);
        this.code = status.getCode();
    }

    public int getCode() {
        return code;
    }

}
