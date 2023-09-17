package com.great.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author Y.X
 * @since 2021/9/12
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    @Primary
    public RedisCacheManager cacheManager(
            RedisConnectionFactory redisConnectionFactory, RedisTemplate<String, Object> redisTemplate) {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(getCacheConfigurationWithTtl(redisTemplate, CacheExpire.MONTH_THREE.getExpire()));

        for (CacheExpire cacheExpire : CacheExpire.values()) {
            if (cacheExpire != CacheExpire.MONTH_THREE) {
                builder.withCacheConfiguration(
                        cacheExpire.getName(),
                        getCacheConfigurationWithTtl(redisTemplate, cacheExpire.getExpire())
                );
            }
        }

        return builder.build();
    }

    private RedisCacheConfiguration getCacheConfigurationWithTtl(
            RedisTemplate<String, Object> template, long seconds) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                // 设置key为String
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
                // 设置value 为自动转Json的Object
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()))
                // 不缓存null
                .disableCachingNullValues()
                // 缓存数据保存seconds
                .entryTtl(Duration.ofSeconds(seconds));
    }

    @Bean
    @ConditionalOnMissingBean(GenericJackson2JsonRedisSerializer.class)
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        ObjectMapper om = new ObjectMapper();

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 属性为Null的不进行序列化，只对pojo起作用，对map和list不起作用
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 遇到未知属性是否抛出异常 ，默认是抛出异常的
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 对json属性进行标记，使反序列化时能正确解析范型
        om.activateDefaultTyping(om.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_OBJECT);
        // 当实体类没有setter方法时，序列化不报错，返回一个空对象
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 设置可以处理LocalDateTime
        om.registerModule(new JavaTimeModule());

        GenericJackson2JsonRedisSerializer.registerNullValueSerializer(om, null);

        return new GenericJackson2JsonRedisSerializer(om);
    }

    @Bean
    @Primary
    @Order(1)
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory factory,
            GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(genericJackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        return template;
    }
}
