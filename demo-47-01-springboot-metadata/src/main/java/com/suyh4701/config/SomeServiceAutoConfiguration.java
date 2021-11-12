package com.suyh4701.config;

import com.suyh4701.properties.SomeServiceProperties;
import com.suyh4701.properties.outer.Outer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 但是这里有一个问题，就是这里面的路径需要被spring 扫描到
 * 对于@EnableConfigurationProperties 与 @ConfigurationProperties 两个注解的解释，参考博客：https://blog.csdn.net/zknxx/article/details/79183698
 *
 * 对于这种不好写在类文件中的配置项，我们可以手动添加到配置文件(additional-spring-configuration-metadata)中。
 */
@Configuration
@ConditionalOnProperty(name = "suyh.service.config.enabled", havingValue = "true", matchIfMissing = true)  // 当指定属性与havingValue 的值匹配时，才会运行该方法
@EnableConfigurationProperties({SomeServiceProperties.class, Outer.class})
public class SomeServiceAutoConfiguration {
    public SomeServiceAutoConfiguration() {
        System.out.println("SomeServiceAutoConfiguration");
    }
    @Resource
    private SomeServiceProperties properties;

    @Resource
    private Outer outer;

    /**
     * 这样看来，Inner 并不会被注册为一个bean 对象，而是new 的一个普通对象
     */
//    @Resource
//    private Inner inner;

    @PostConstruct
    public void init() {
        System.out.println("SomeServiceProperties: " + properties);
    }
}