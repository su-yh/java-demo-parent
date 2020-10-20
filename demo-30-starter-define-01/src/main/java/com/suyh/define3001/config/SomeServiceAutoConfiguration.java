package com.suyh.define3001.config;


import com.suyh.define3001.service.SomeService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnMissingBean(SomeService.class)
@EnableConfigurationProperties(SomeServiceProperties.class)
public class SomeServiceAutoConfiguration {
    @Resource
    private SomeServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean   // 如果这个类型的Bean 对象不存在，则执行下面的代码生成一个Bean
    public SomeService someService() {
        return new SomeService(properties.getPrefix(), properties.getSuffix());
    }
}
