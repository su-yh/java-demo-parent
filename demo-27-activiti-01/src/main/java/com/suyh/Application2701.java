package com.suyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.suyh.mapper")
public class Application2701 {
    public static void main(String[] args) {
        SpringApplication.run(Application2701.class, args);
    }
}
