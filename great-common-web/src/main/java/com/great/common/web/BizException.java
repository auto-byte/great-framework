package com.great.common.web;

/**
 * 业务异常，表示该异常由相关业务造成，继承自 {@link StatusException}
 * Created on 2021/9/8 14:27
 *
 * @author Y.X
 */
public class BizException extends StatusException {

    public BizException(String message) {
        super(BizStatus.OPT_FAIL.getCode(), message);
    }

    public BizException(int code, String message) {
        super(code, message);
    }

    public BizException(StatusResult status) {
        super(status);
    }

    public BizException(StatusResult status, String message) {
        super(status, message);
    }

    public BizException(StatusResult status, Throwable cause) {
        super(status.getCode(), status.getMessage(), cause);
    }

    public BizException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
