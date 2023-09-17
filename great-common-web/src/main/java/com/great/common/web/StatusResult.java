package com.great.common.web;

/**
 * Created on 2021/8/27 13:45
 * 结果状态
 *
 * @author Y.X
 */
public interface StatusResult {
    /**
     * 获取状态码
     *
     * @return 状态码
     */
    default int getCode() {
        return 0;
    }

    /**
     * 获取提示信息
     *
     * @return 信息
     */
    default String getMessage() {
        return "";
    }
}
