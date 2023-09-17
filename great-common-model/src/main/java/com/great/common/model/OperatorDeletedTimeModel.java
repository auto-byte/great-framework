package com.great.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created on 2021/9/18 10:21
 *
 * @author Y.X
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperatorDeletedTimeModel extends DeletedTimeModel {

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
}
