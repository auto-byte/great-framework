package com.great.common.web;

import java.util.Objects;

/**
 * Created on 2020/11/24 9:22 上午
 *
 * @author Y.X
 * @since 1.0.0
 */
public abstract class ResultUtils {
    /**
     * 判断Result结果是否正确
     *
     * @param result 结果对象
     * @param <T>    数据类型
     * @return true 不为null并且code为NormalStatus.SUCCESS则返回
     */
    public static <T> boolean isSuccess(Result<T> result) {
        return result != null && Objects.equals(result.getCode(), NormalStatus.SUCCESS.getCode());
    }

    /**
     * 判断Result结果是否正确并且结果中有DATA
     *
     * @param result 结果对象
     * @param <T>    数据类型
     * @return true 状态值为成功，并且有DATA
     */
    public static <T> boolean isSuccessData(Result<T> result) {
        return isSuccess(result) && result.getData() != null;
    }

    /**
     * 是否失败，一般在接口调用时，网络等问题下没有返回结果时为null
     *
     * @param result 结果对象
     * @param <T>    数据类型
     * @return true result == null, 没有返回正确的对象
     */
    public static <T> boolean isNull(Result<T> result) {
        return result == null;
    }

    /**
     * 是否是服务间调用异常
     *
     * @param result 结果对象
     * @param <T>    数据类型
     * @return true 表示 null 或 code = 51001
     */
    public static <T> boolean isCallErr(Result<T> result) {
        return isNull(result) || Objects.equals(result.getCode(), SysStatus.SYS_CALL_ERR.getCode());
    }

    /**
     * 判断Result结果是否错误
     *
     * @param result 结果对象
     * @param <T>    数据类型
     * @return true 与isSuccess相反
     */
    public static <T> boolean isFail(Result<T> result) {
        return !isSuccess(result);
    }

    /**
     * 获取result的code，可以避免空指针异常
     *
     * @param result 结果对象
     * @param <T>    数据类型
     * @return 如果result为null, 则返回null, 否则直接返回result.code
     */
    public static <T> Integer getOrErrCode(Result<T> result) {
        if (result == null) {
            return SysStatus.SYS_ERR.getCode();
        }
        return result.getCode();
    }

    /**
     * 获取result的message, 可以避免空指针异常
     *
     * @param result 结果对象
     * @param <T>    数据类型
     * @return 如果result为null或message为null都返回 ""
     */
    public static <T> String getOrErrMessage(Result<T> result) {
        if (result == null) {
            return SysStatus.SYS_ERR.getMessage();
        }
        String message = result.getMessage();
        return message == null ? "" : message;
    }

    /**
     * 获取result的data, 可以避免空指针异常
     *
     * @param result 结果对象
     * @param <T>    数据类型
     * @return 如果result为null返回null, 否则直接返回result.data
     */
    public static <T> T getData(Result<T> result) {
        if (result == null) {
            return null;
        }
        return result.getData();
    }
}
