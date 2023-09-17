package com.great.common.web;

/**
 * Created on 2021/8/27 13:48
 * 带有数据的结果
 * @author Y.X
 */
public interface DataResult<T>{
    /**
     * 获取结果
     * @return 结果
     */
    T getData();
}
