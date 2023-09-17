package com.great.lock;

/**
 * Created on 2021/9/15 19:37
 *
 * @author Y.X
 */
public enum LockType {
    REENTRANT,
    READ,
    WRITE,
    FAIR,
    SPIN,
    MULTI;
}
