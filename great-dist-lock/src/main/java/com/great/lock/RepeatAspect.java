package com.great.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/9/15 20:23
 * 锁执行尽量在后，保证事务处理完成
 * @author Y.X
 */
@Order(100)
@Aspect
@Component
public class RepeatAspect {
    private static final Logger log = LoggerFactory.getLogger(RepeatAspect.class);

    private final LockKeyParser keyParser = new LockKeyParser();

    private final RedissonClient redisson;

    public RepeatAspect(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Around(value = "execution(public * com.great.*.controller..*.*(..)) && @annotation(repeatLock)")
    public Object lockPointcut(ProceedingJoinPoint joinPoint, NonRepeatable repeatLock) throws Throwable {
        String keyValue = keyParser.keyValues(joinPoint, new String[]{repeatLock.key()}).get(0);
        if (!redisson.getBucket(keyValue).trySet("repeated", repeatLock.waitTime(), repeatLock.unit())) {
            throw new com.great.lock.LockedException(LockStatus.LOCKED, repeatLock.message());
        }
        return joinPoint.proceed();
    }
}
