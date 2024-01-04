package com.suyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 当不连接数据库的时候需要添加排除数据库连接信息，不然会有错误提示
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShiroDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroDemoApplication.class, args);
    }
}
