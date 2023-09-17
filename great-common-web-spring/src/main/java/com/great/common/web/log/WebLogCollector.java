package com.great.common.web.log;

import org.aspectj.lang.JoinPoint;

/**
 * Created on 2021/11/23 12:27
 *
 * @author Y.X
 */
public interface WebLogCollector<T> {

    T collect(JoinPoint joinPoint);
}
