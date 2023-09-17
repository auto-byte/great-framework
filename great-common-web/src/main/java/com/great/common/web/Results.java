package com.great.common.web;

/**
 * Created on 2021/8/27 13:57
 * Result的快速构建类
 *
 * @author Y.X
 */
public abstract class Results {
    /**
     * 成功结果
     *
     * @return 返回操作成功的 {@link Result}
     */
    public static <T> Result<T> success() {
        return of(NormalStatus.SUCCESS);
    }

    /**
     * 有数据的成功结果
     *
     * @param data 返回的数据
     * @param <T>  数据类型
     * @return 返回操作成功的 {@link Result}
     */
    public static <T> Result<T> success(T data) {
        return of(NormalStatus.SUCCESS, data);
    }


    /**
     * 成功结果，使用默认的code
     *
     * @param message 提示信息
     * @return 返回操作成功的 {@link Result}
     */
    public static <T> Result<T> successMsg(String message) {
        return of(NormalStatus.SUCCESS.getCode(), message, null);
    }

    /**
     * 成功结果，使用默认的code
     *
     * @param message 自定义提示信息
     * @param data    数据
     * @param <T>     数据类型
     * @return 返回操作成功的 {@link Result}
     */
    public static <T> Result<T> success(String message, T data) {
        return of(NormalStatus.SUCCESS.getCode(), message, data);
    }

    /**
     * 结果
     *
     * @param status 自定义 {@link StatusResult}
     * @param <T>    数据类型
     * @return 返回操作成功的 {@link Result}
     */
    public static <T> Result<T> of(StatusResult status) {
        return of(status, null);
    }

    /**
     * 新建result
     *
     * @param status 自定义{@link StatusResult}
     * @param data   数据
     * @param <T>    数据类型
     * @return 返回操作成功的 {@link Result}
     */
    public static <T> Result<T> of(StatusResult status, T data) {
        return new Result<>(status.getCode(), status.getMessage(), data);
    }

    /**
     * 结果
     *
     * @param code    状态码
     * @param message 提示
     * @param <T>     数据类型
     * @return 返回 {@link Result}
     */
    public static <T> Result<T> of(int code, String message) {
        return of(code, message, null);
    }

    /**
     * 结果
     *
     * @param code    状态
     * @param message 提示
     * @param data    数据
     * @param <T>     数据类型
     * @return 返回 {@link Result}
     */
    public static <T> Result<T> of(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    /**
     * 失败结果，默认的失败结果
     *
     * @return 返回失败的 {@link Result}
     */
    public static <T> Result<T> error() {
        return error(SysStatus.SYS_ERR);
    }

    /**
     * 失败结果，默认的失败结果
     *
     * @param message 自定义提示信息
     * @return 返回失败的 {@link Result}
     */
    public static <T> Result<T> error(String message) {
        return error(SysStatus.SYS_ERR, message);
    }

    /**
     * 失败结果
     *
     * @param status 自定义 {@link StatusResult}
     * @return 返回操作成功的 {@link Result}
     */
    public static <T> Result<T> error(StatusResult status) {
        return new Result<T>(status.getCode(), status.getMessage(), null);
    }

    /**
     * 失败结果
     *
     * @param status  自定义 {@link StatusResult}
     * @param message 自定义提示信息
     * @return 返回操作成功的 {@link Result}
     */
    public static <T> Result<T> error(StatusResult status, String message) {
        return new Result<T>(status.getCode(), message, null);
    }
}
