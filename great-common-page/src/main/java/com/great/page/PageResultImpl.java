package com.great.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created on 2020/10/29 1:30 下午
 *
 * @author Y.X
 * @version 1.0.0
 */
public class PageResultImpl<T> implements PageResult<T> {
    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 当前分页大小
     */
    private Integer pageSize;

    /**
     * 当前数据总数
     */
    private Long total;

    /**
     * 数据集合
     */
    private Collection<T> list;

    /**
     * 初始化
     */
    public PageResultImpl() {
        this.pageNum = PageCons.DEFAULT_PAGE_NUM;
        this.pageSize = PageCons.DEFAULT_PAGE_SIZE;
        this.total = 0L;
        this.list = Collections.emptyList();
    }

    /**
     * 分页结果转换
     *
     * @param function 转换函数
     * @param <R>      结果类型
     * @return 新的分页集
     */
    @Override
    public <R> PageResultImpl<R> map(Function<T, R> function) {
        PageResultImpl<R> pageResult = new PageResultImpl<>();

        pageResult.setPageNum(this.getPageNum());
        pageResult.setPageSize(this.getPageSize());
        pageResult.setTotal(this.getTotal());

        if (this.getList() == null || this.getList().isEmpty()) {
            pageResult.setList(new ArrayList<>(0));
        } else {
            pageResult.setList(
                    this.getList()
                            .stream()
                            .map(function)
                            .collect(Collectors.toList())
            );
        }

        return pageResult;
    }

    @Override
    public Integer getPageNum() {
        return this.pageNum;
    }

    @Override
    public void setPageNum(Integer pageNum) {
        if (pageNum != null && pageNum > 0) {
            this.pageNum = pageNum;
        }
    }

    @Override
    public Integer getPageSize() {
        return this.pageSize;
    }

    @Override
    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    @Override
    public Long getTotal() {
        return this.total;
    }

    @Override
    public void setTotal(Long total) {
        if (total != null && total > 0) {
            this.total = total;
        }
    }

    @Override
    public Collection<T> getList() {
        return this.list;
    }

    @Override
    public void setList(Collection<T> list) {
        if (list != null) {
            this.list = list;
        }
    }
}
