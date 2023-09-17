package com.great.lock;


import com.great.common.web.StatusResult;

/**
 * Created on 2021/9/16 16:46
 *
 * @author Y.X
 */
public enum LockStatus implements StatusResult {

    LOCKED(51001, "资源已锁定, 请稍后再试"),

    LOCK_ACQ_FAIL(51002, "获取锁失败"),
    ;

    private final int code;

    private final String message;

    LockStatus(int code, String message) {
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
