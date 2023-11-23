package com.suyh3701;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author suyh
 * @since 2023-11-23
 */
@SpringBootApplication
public class DemoApplication37 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication37.class, args);

        // 对于spring boot 应用，我们并不需要主动去注册shutdown hook，在org.springframework.boot.SpringApplication.refreshContext() 方法中springboot 已经注册上了。
        // 只有原生的spring 应用才需要手动去注册一个shutdown hook
        // context.registerShutdownHook();

        // 正常关闭，优雅停止spring boot 应用，它会发送相关事件，并回收相关资源。
        context.close();
    }
}
