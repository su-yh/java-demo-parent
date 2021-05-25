package com.suyh4001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application4001 {
    public static void main(String[] args) {
        // suyh - 心跳发送间隔时间，单位: ms
        System.setProperty("csp.sentinel.heartbeat.interval.ms","10000");
        SpringApplication.run(Application4001.class, args);
    }
}
