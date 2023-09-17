package com.great.lock;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2021/9/15 19:56
 * 防重复锁
 *
 * @author Y.X
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonRepeatable {

    String key() default "";

    long waitTime() default 600;

    TimeUnit unit() default TimeUnit.MILLISECONDS;

    String message() default "操作频繁";
}
