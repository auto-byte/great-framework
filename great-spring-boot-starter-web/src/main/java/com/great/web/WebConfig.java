package com.great.web;

import com.great.common.web.log.DefaultAspectLogListener;
import com.great.common.web.log.WebLogListener;
import com.great.common.web.log.WebLogProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2021/9/8 14:46
 *
 * @author Y.X
 */
@Configuration
@ComponentScan(value = "com.great.common.web")
@EnableConfigurationProperties(WebLogProps.class)
public class WebConfig {

    @Bean
    public WebLogListener webLogListener() {
        return new DefaultAspectLogListener();
    }
}
