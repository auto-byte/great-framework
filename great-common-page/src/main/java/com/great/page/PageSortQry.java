package com.great.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created on 2021/8/27 14:53
 *
 * @author Y.X
 */
public class PageSortQry extends PageQry {
    /**
     * 排序的数据，如 [name:asc, age:desc]，默认排序是asc
     */
    private List<String> orders;

    public PageSortQry() {
        super();
    }

    public PageSortQry(List<String> orders) {
        super();
        this.orders = orders;
    }

    public PageSortQry(Integer pageNum, Integer pageSize, List<String> orders) {
        super(pageNum, pageSize);
        this.orders = orders;
    }

    public static PageSortQry of(Integer pageNum, Integer pageSize, List<String> orders) {
        return new PageSortQry(pageNum, pageSize, orders);
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders == null ? Collections.emptyList() : orders;
    }

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public String getOrderBy() {
        List<SortField> sortFields = getOrderFields();
        StringJoiner sj = new StringJoiner(",");
        for (SortField sortField : sortFields) {
            sj.add(sortField.getField() + " " + sortField.getOrder().name());
        }
        return sj.toString();
    }

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public List<SortField> getOrderFields() {
        if (this.orders == null || this.orders.isEmpty()) {
            return Collections.emptyList();
        }

        List<SortField> sortFields = new ArrayList<>(this.orders.size());
        for (String order : this.orders) {
            if (order == null || order.trim().equals("")) {
                continue;
            }

            String[] split = order.split(":");
            SortField sortField = new SortField(split[0]);
            if (split.length > 1
                    && SortDirect.DESC.name().equalsIgnoreCase(split[1])) {
                sortField.setOrder(SortDirect.DESC);
            }

            sortFields.add(sortField);
        }
        return sortFields;
    }
}
