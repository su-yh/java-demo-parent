package com.suyh0310.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching(proxyTargetClass = true)
@Configuration(proxyBeanMethods = false)
@Slf4j
public class RedisAutoConfiguration {
//    @Bean
//    public SuyhRedisCacheManagerBuilderCustomizer suyhRedisCacheManagerBuilderCustomizer() {
//        return new SuyhRedisCacheManagerBuilderCustomizer();
//    }
}
