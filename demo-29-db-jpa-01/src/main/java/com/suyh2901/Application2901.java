package com.suyh2901;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // 使用这个注解 @CreatedBy @CreatedDate 才会生效，这里可以指定Bean
public class Application2901 {
    public static void main(String[] args) {
        SpringApplication.run(Application2901.class, args);
    }
}
