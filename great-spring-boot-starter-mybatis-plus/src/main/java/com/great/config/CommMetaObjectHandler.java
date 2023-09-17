package com.great.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * Created on 2021/8/30 19:29
 *
 * @author Y.X
 */
public class CommMetaObjectHandler implements MetaObjectHandler, OperateUser {

    private OperateUser currentUser;

    public CommMetaObjectHandler() {
    }

    public CommMetaObjectHandler(OperateUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public Long getCurrentUserId() {
        Long userId = 0L;
        if (currentUser != null) {
            userId = currentUser.getCurrentUserId();
        }
        return userId == null ? 0L : userId;
    }

    public void insertFill(MetaObject metaObject) {
        if (this.hasSetterAndIsNull(metaObject, "createTime")) {
            metaObject.setValue("createTime", LocalDateTime.now());
        }

        if (this.hasSetterAndIsNull(metaObject, "updateTime")) {
            metaObject.setValue("updateTime", LocalDateTime.now());
        }

        if (this.hasSetterAndIsNull(metaObject, "createAt")) {
            metaObject.setValue("createAt", LocalDateTime.now());
        }

        if (this.hasSetterAndIsNull(metaObject, "updateAt")) {
            metaObject.setValue("updateAt", LocalDateTime.now());
        }

        if (this.hasSetterAndIsNull(metaObject, "deleted")) {
            metaObject.setValue("deleted", 0);
        }

        Long userId = getCurrentUserId();
        if (this.hasSetterAndIsNull(metaObject, "createBy")) {
            metaObject.setValue("createBy", userId);
        }

        if (this.hasSetterAndIsNull(metaObject, "updateBy")) {
            metaObject.setValue("updateBy", userId);
        }
    }

    public void updateFill(MetaObject metaObject) {
        if (this.hasSetterAndIsNull(metaObject, "updateBy")) {
            metaObject.setValue("updateBy", getCurrentUserId());
        }

        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", LocalDateTime.now());
        }

        if (this.hasSetterAndIsNull(metaObject, "updateAt")) {
            metaObject.setValue("updateAt", LocalDateTime.now());
        }
    }

    protected boolean hasSetterAndIsNull(MetaObject metaObject, String fieldName) {
        return metaObject.hasGetter(fieldName) && metaObject.getValue(fieldName) == null;
    }

    public OperateUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(OperateUser currentUser) {
        this.currentUser = currentUser;
    }
}