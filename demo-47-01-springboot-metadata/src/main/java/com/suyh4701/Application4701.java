package com.suyh4701;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class Application4701 {
    public static void main(String[] args) {
        SpringApplication.run(Application4701.class, args);
        System.out.println("finished.");
    }
}
