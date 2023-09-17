package com.great.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2021/9/15 19:59
 *
 * @author Y.X
 */
@Aspect
@Component
public class LockAspect {

    private static final Logger log = LoggerFactory.getLogger(LockAspect.class);

    private final LockKeyParser keyParser = new LockKeyParser();

    private final RedissonClient redisson;

    public LockAspect(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Around(value = "@annotation(distLock)")
    public Object lockAround(ProceedingJoinPoint joinPoint, DistLock distLock) throws Throwable {
        RLock rLock = getLock(distLock.type(), keyParser.keyValues(joinPoint, distLock.key()));

        long waitTime = distLock.waitTime();
        long leaseTime = distLock.leaseTime();
        TimeUnit unit = distLock.unit();

        if (distLock.fastFail() && rLock.isLocked()) {
            throw new LockedException(LockStatus.LOCKED, distLock.fastFailMsg());
        }

        if (waitTime <= -1 && leaseTime <= -1) {
            rLock.lock();
        } else if (waitTime <= -1) {
            rLock.lock(leaseTime, unit);
        } else if (!rLock.tryLock(waitTime, leaseTime, unit)) {
            throw new LockAcquireException(LockStatus.LOCK_ACQ_FAIL, "未获取到锁");
        }

        if (log.isDebugEnabled()) {
            log.debug("thread: {} get {} lock.",
                    Thread.currentThread().getId(),
                    ((MethodSignature) joinPoint.getSignature()).getMethod());
        }

        try {
            return joinPoint.proceed();
        } finally {
            rLock.unlock();
        }
    }

    private RLock getLock(LockType lockType, List<String> keys) {
        switch (lockType) {
            case FAIR:
                return redisson.getFairLock(keys.get(0));
            case READ:
                return redisson.getReadWriteLock(keys.get(0)).readLock();
            case WRITE:
                return redisson.getReadWriteLock(keys.get(0)).writeLock();
            case SPIN:
                return redisson.getSpinLock(keys.get(0));
            case MULTI:
                RLock[] rLocks = new RLock[keys.size()];
                for (int i = 0; i < keys.size(); i++) {
                    rLocks[i] = redisson.getLock(keys.get(i));
                }
                return redisson.getMultiLock(rLocks);
            default:
                return redisson.getLock(keys.get(0));
        }
    }
}
