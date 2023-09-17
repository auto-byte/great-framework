package com.great.page;

import java.util.Collection;

/**
 * Created on 2021/8/27 17:48
 *
 * @author Y.X
 */
public abstract class PageResults {

    public static <T> PageResult<T> of(Integer pageNum,
                                       Integer pageSize,
                                       Long total,
                                       Collection<T> list) {
        PageResult<T> pageResult = new PageResultImpl<>();
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setTotal(total);
        pageResult.setList(list);
        return pageResult;
    }

    public static <T> PageResult<T> of(Integer pageNum,
                                       Integer pageSize) {
        return of(pageNum, pageSize, null, null);
    }

    public static <T> PageResult<T> of(PageQry pageQry) {
        return of(pageQry.getPageNum(), pageQry.getPageSize());
    }

    public static <T> PageResult<T> empty() {
        return new PageResultImpl<>();
    }
}
