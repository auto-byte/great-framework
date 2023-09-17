package com.great.lock;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2021/9/15 19:34
 * <p>
 * 基于Redisson的分布式锁
 *
 * @author Y.X
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistLock {

    /**
     * 分布式锁类型
     */
    LockType type() default LockType.REENTRANT;

    /**
     * 锁key, 支持SpEL表达式
     */
    String[] key() default "";

    /**
     * 加锁等待时间 默认忽略
     */
    long waitTime() default -1;

    /**
     * 释放时间 默认忽略
     */
    long leaseTime() default -1;

    /**
     * 时间单位 默认微秒
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;

    /**
     * 使用tryLock,如果失败则终止操作并提示相关信息
     */
    boolean fastFail() default false;

    /**
     * tryLock失败后的信息
     */
    String fastFailMsg() default "操作频繁";
}
