package com.suyh2902;


import com.suyh2902.custom.impl.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// 启用自定义的repository 来替代jpa 原生的repository
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class Application2902 {
    public static void main(String[] args) {
        SpringApplication.run(Application2902.class, args);
    }
}