package com.great.lock;


import com.great.common.web.StatusException;
import com.great.common.web.StatusResult;

/**
 * Created on 2021/9/17 10:23
 *
 * @author Y.X
 */
public class LockAcquireException extends StatusException {

    public LockAcquireException(int code, String message) {
        super(code, message);
    }

    public LockAcquireException(String message) {
        super(message);
    }

    public LockAcquireException(StatusResult status) {
        super(status);
    }

    public LockAcquireException(StatusResult status, String message) {
        super(status, message);
    }

    public LockAcquireException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public LockAcquireException(StatusResult status, Throwable cause) {
        super(status, cause);
    }
}
