package com.great.lock;


import com.great.common.web.StatusException;
import com.great.common.web.StatusResult;

/**
 * Created on 2021/9/16 10:58
 *
 * @author Y.X
 */
public class LockedException extends StatusException {

    public LockedException(int code, String message) {
        super(code, message);
    }

    public LockedException(String message) {
        super(message);
    }

    public LockedException(StatusResult status) {
        super(status);
    }

    public LockedException(StatusResult status, String message) {
        super(status, message);
    }

    public LockedException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public LockedException(StatusResult status, Throwable cause) {
        super(status, cause);
    }
}
