package com.suyh.define3001.config;


import com.suyh.define3001.service.SomeService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 但是这里有一个问题，就是这里面的路径需要被spring 扫描到
 * 对于@EnableConfigurationProperties 与 @ConfigurationProperties 两个注解的解释，参考博客：https://blog.csdn.net/zknxx/article/details/79183698
 */
@Configuration
@EnableConfigurationProperties(SomeServiceProperties.class)
public class SomeServiceAutoConfiguration {
    @Resource
    private SomeServiceProperties properties;

    @Bean
    // matchIfMissing 若没有配置该配置，则其结果与指定了该属性值为true 的效果相同，即指定了该属性的默认值为true
    @ConditionalOnProperty(name = "some.service.enable", havingValue = "true", matchIfMissing = true)  // 当指定属性与havingValue 的值匹配时，才会运行该方法
    @ConditionalOnMissingBean
    public SomeService someService() {
        return new SomeService(properties.getPrefix(), properties.getSuffix());
    }

    @Bean
    @ConditionalOnMissingBean  // 如果这个类型的Bean 对象不存在，则执行下面的代码生成一个Bean
    public SomeService someService2() {
        return new SomeService("", "");
    }

}
