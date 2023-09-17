package com.great.config;


import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * Created on 2021/8/30 19:18
 *
 * @author Y.X
 */
@Configuration
@ConditionalOnProperty(value = "xxl.job.enable", havingValue = "true")
@EnableConfigurationProperties(value = XxlJobProperties.class)
public class XxlJobConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobProperties props) {
        XxlJobSpringExecutor executor = new XxlJobSpringExecutor();
        executor.setAdminAddresses(props.getAddress());
        executor.setAccessToken(props.getAccessToken());

        Optional.ofNullable(props.getExecutor()).ifPresent(exe -> {
            executor.setAppname(exe.getAppName() == null ? applicationName : exe.getAppName());
            executor.setAddress(exe.getAddress());
            executor.setIp(exe.getIp());
            executor.setPort(exe.getPort());
            executor.setLogPath(exe.getLogPath());
            executor.setLogRetentionDays(exe.getLogRetentionDays());
        });

        return executor;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
