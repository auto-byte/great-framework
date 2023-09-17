package com.great.feign;

import feign.Feign;
import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2021/9/15 15:42
 *
 * @author Y.X
 */
@Configuration
public class FeignConfig {

    @Bean
    @ConditionalOnClass(Feign.class)
    @ConditionalOnMissingBean(Logger.Level.class)
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
