package com.suyh.define3001.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这个注解，可以使得，未被springboot 启动类扫描到的类，也可以被spring管理到
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({SomeServiceAutoConfiguration.class})
public @interface EnableWrap {
}
