package com.great.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 包含软删除和基本时间的基本Model
 * </p>
 *
 * @author Y.X
 * @since 2021-08-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeletedTimeModel extends TimeModel {
    /**
     * 是否删除 0.否 1.是
     */
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted")
    @JsonIgnore
    private Integer deleted;
}
