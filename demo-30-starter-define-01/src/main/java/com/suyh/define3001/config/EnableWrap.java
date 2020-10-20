package com.suyh.define3001.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这个注解，可以使得，未被springboot 启动类扫描到的类，也可以被spring管理到
 *
 * spring-boot 认为这里面的这个配置下面的类都有可能是你会用到的Bean 对象。在启动的时候提前给导入进来。
 * META-INF/spring.factories
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration
 *
 * spring-boot-actuator-autoconfigure 依赖包中
 *
 * `@EnableAutoConfiguration`
 *
 * 如果想要不使用注解也能被spring管理，那么可以添加自己的 META-INF/spring.factories
 * 在里面添加配置
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 * com.suyh.define3001.config.SomeServiceAutoConfiguration
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({SomeServiceAutoConfiguration.class})
public @interface EnableWrap {
}
