package com.great.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created on 2021/1/29 1:48 下午
 *
 * @author Y.X
 * @since v1.2.0
 */
public class PageQry implements Serializable {
    /**
     * 页码
     */
    private Integer pageNum = PageCons.DEFAULT_PAGE_NUM;

    /**
     * 分页大小
     */
    private Integer pageSize = PageCons.DEFAULT_PAGE_SIZE;

    public PageQry() {
    }

    public PageQry(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum <= 0) {
            this.pageNum = PageCons.DEFAULT_PAGE_NUM;
        } else {
            this.pageNum = pageNum;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize <= 0) {
            this.pageSize = PageCons.DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public Integer getIndex() {
        return (pageNum - 1) * pageSize;
    }

    public PageQry of(Integer pageNum, Integer pageSize) {
        PageQry pageQry = new PageQry();
        pageQry.setPageNum(pageNum);
        pageQry.setPageSize(pageSize);
        return pageQry;
    }
}
