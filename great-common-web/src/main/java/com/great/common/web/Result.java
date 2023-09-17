package com.great.common.web;

import java.io.Serializable;

/**
 * Created on 2021/8/27 13:53
 * 结果返回的封装对象
 *
 * @author Y.X
 */
public class Result<T> implements DataResult<T>, StatusResult, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务器时间戳
     */
    private final long timestamp = System.currentTimeMillis();

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public Result() {

    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public int getCode() {
        // 返回 -1 表示未定义
        return this.code == null ? -1 : this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
