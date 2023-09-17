package com.great.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created on 2021/9/18 10:16
 *
 * @author Y.X
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperatorTimeModel extends TimeModel {

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
}
