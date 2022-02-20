package com.suyh0403.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
public class Demo0403Configuration {

    @ConditionalOnMissingBean(LockProvider.class)
    @Bean
    public LockProvider lockProvider2(DataSource dataSource) {
        JdbcTemplateLockProvider.Configuration shdlck = JdbcTemplateLockProvider.Configuration.builder()
                .withJdbcTemplate(new JdbcTemplate(dataSource))
                .usingDbTime() // Works on Postgres, MySQL, MariaDb, MS SQL, Oracle, DB2, HSQL and H2
                .build();
        return new JdbcTemplateLockProvider(shdlck);
    }

    @ConditionalOnMissingBean(LockProvider.class)
    @Bean
    public LockProvider lockProvider3(DataSource dataSource) {
        JdbcTemplateLockProvider.Configuration shdlck = JdbcTemplateLockProvider.Configuration.builder()
                .withTableName("shdlck")
                .withJdbcTemplate(new JdbcTemplate(dataSource))
                .withLockedByValue("my-value")
                .build();
        return new JdbcTemplateLockProvider(shdlck);
    }

    @ConditionalOnMissingBean(LockProvider.class)
    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource, "shedlock");
    }

}
