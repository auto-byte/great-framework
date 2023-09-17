package com.great.page;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Y.X
 * @since 2021/9/8
 */
public class SortField {
    /**
     * 排序字段
     */
    @ApiModelProperty(hidden = true)
    private String field;

    /**
     * 排序方向
     */
    @ApiModelProperty(hidden = true)
    private SortDirect order;

    public SortField() {
        this.order = SortDirect.ASC;
    }

    public SortField(String field) {
        this();
        this.field = field;
    }

    public SortField(String field, SortDirect order) {
        this.field = field;
        this.order = order;
    }

    public boolean isAsc() {
        return this.order == null || this.order == SortDirect.ASC;
    }

    public boolean isDesc() {
        return this.order != null && this.order == SortDirect.DESC;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public SortDirect getOrder() {
        return order;
    }

    public void setOrder(SortDirect order) {
        this.order = order;
    }
}
