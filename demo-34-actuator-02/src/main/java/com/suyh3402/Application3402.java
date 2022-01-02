package com.suyh3402;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class Application3402 {
    public static void main(String[] args) {
        SpringApplication.run(Application3402.class, args);
    }
}
