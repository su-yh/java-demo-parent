package com.suyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-05-04 17:51
 */
@SpringBootApplication
@MapperScan("com.suyh.mapper")  // 注意这个注解，要使用  tk.mybatis.*** 这个包下的，不能使用mybatis 提供的。
public class CommonMapperApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonMapperApplication.class, args);
    }
}
