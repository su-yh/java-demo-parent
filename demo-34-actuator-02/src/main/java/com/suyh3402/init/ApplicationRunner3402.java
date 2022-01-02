package com.suyh3402.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Component
@Slf4j
public class ApplicationRunner3402 implements ApplicationRunner {
    @Resource
    private DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("datasource: {}", dataSource.getClass().getSimpleName());
    }
}
