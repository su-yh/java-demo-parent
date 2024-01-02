package com.suyh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {
    static {
        // 指定日志配置文件所在路径，默认情况下是在resources 目录下面找
        System.setProperty("log4j.configurationFile", "config/log4j2.xml");
        System.setProperty("log4j2.dir", "demo-04-springboot-test");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        log.info("begin...");

        log.debug("debug log.");
        log.info("info log.");
        log.warn("warn log.");
    }
}
