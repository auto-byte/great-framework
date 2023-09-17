package com.great.common.web;

import java.lang.annotation.*;

/**
 * 用于主动封装 {@see com.great.web.Result}
 * 将返回值使用Result进行包装
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
}
