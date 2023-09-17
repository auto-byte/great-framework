package com.great.config;

/**
 * Created on 2021/11/30 10:40
 *
 * @author Y.X
 */
public class NonOperateUser implements OperateUser {
    @Override
    public Long getCurrentUserId() {
        return 0L;
    }
}
