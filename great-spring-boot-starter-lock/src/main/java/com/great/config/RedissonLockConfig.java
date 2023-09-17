package com.great.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2021/9/16 11:11
 *
 * @author Y.X
 */
@Configuration
@ComponentScan(basePackages = "com.great.lock")
public class RedissonLockConfig {
}
