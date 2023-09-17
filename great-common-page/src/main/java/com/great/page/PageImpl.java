package com.great.page;

import com.github.pagehelper.IPage;

import java.util.List;

/**
 * Created on 2021/11/16 11:54
 *
 * @author Y.X
 */
public class PageImpl implements IPage {

    private final PageSortQry pageSortQry;

    public PageImpl(PageSortQry pageSortQry) {
        this.pageSortQry = pageSortQry;
    }

    public static IPage of(Integer pageNum, Integer pageSize, List<String> orders) {
        return new PageImpl(PageSortQry.of(pageNum, pageSize, orders));
    }

    @Override
    public Integer getPageNum() {
        return pageSortQry.getPageNum();
    }

    @Override
    public Integer getPageSize() {
        return pageSortQry.getPageSize();
    }

    @Override
    public String getOrderBy() {
        return pageSortQry.getOrderBy();
    }

}
