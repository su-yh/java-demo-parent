package com.suyh0310.config;

import com.suyh0310.cache.SuyhRedisCacheManagerBuilderCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling
@EnableCaching(proxyTargetClass = true)
@Configuration(proxyBeanMethods = false)
@Slf4j
public class RedisAutoConfiguration {
    @Bean
    public SuyhRedisCacheManagerBuilderCustomizer suyhRedisCacheManagerBuilderCustomizer() {
        return new SuyhRedisCacheManagerBuilderCustomizer();
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(initialDelay = 10000, fixedRate = 6000)
    public void reportCurrentTime() {
        log.info("Task2 现在时间：" + dateFormat.format(new Date()));
    }
}
