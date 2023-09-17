package com.great.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2021/8/30 19:31
 *
 * @author Y.X
 */
@Configuration
public class MyBatisPlusConfig {

    @Bean
    @ConditionalOnMissingBean({MetaObjectHandler.class})
    public MetaObjectHandler metaObjectHandler(OperateUser currentUser) {
        return new CommMetaObjectHandler(currentUser);
    }

    @Bean
    @ConditionalOnMissingBean({OperateUser.class})
    public OperateUser operateUser() {
        return new NonOperateUser();
    }

    @Bean
    @ConditionalOnMissingBean({MybatisPlusInterceptor.class})
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
