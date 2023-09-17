package com.great.page;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.Function;

/**
 * Created on 2020/10/29 1:23 下午
 * 分页包接口
 *
 * @author Y.X
 * @version 1.0.0
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, defaultImpl = PageResultImpl.class)
public interface PageResult<T> extends Serializable {
    /**
     * 获取 pageNum
     *
     * @return pageNum
     */
    Integer getPageNum();

    /**
     * 设置pageNum
     *
     * @param pageNum pageNum
     */
    void setPageNum(Integer pageNum);

    /**
     * 获取pageSize
     *
     * @return pageSize
     */
    Integer getPageSize();

    /**
     * 设置pageSize
     *
     * @param pageSize pageSize
     */
    void setPageSize(Integer pageSize);

    /**
     * 获取总数
     *
     * @return 总数
     */
    Long getTotal();

    /**
     * 设置总数
     *
     * @param total 总数
     */
    void setTotal(Long total);

    /**
     * 获取数据
     *
     * @return 数据
     */
    Collection<T> getList();

    /**
     * 设置数据
     *
     * @param list 数据
     */
    void setList(Collection<T> list);

    /**
     * 分页结果转换
     *
     * @param function 转换函数
     * @param <R>      结果类型
     * @return 新的分页集
     */
    <R> PageResult<R> map(Function<T, R> function);
}
