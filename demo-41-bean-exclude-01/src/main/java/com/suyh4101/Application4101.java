package com.suyh4101;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 这里将指定的CommonInit 排除掉，那么它将不会被注册到spring 中
 */
@SpringBootApplication
@ComponentScan(
        basePackages = "com.suyh4101",
        // 使得一个bean 不再生效，将其排除在spring 容器中。
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {CommonInit.class,}))
@Slf4j
public class Application4101 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application4101.class, args);
        try {
            context.getBean(CommonInit.class);
            // 不会走到这里
            log.error("cannot enter here.");
        } catch (BeansException e) {
            // 进入这里就对了，因为这个Bean 就排除掉了，所以它不会被注册到spring ioc容器中。
            log.info("no CommonInit bean ");
        }
    }
}
