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
    public LockProvider lockProvider3(DataSource dataSource) {
        JdbcTemplateLockProvider.ColumnNames columnNames = new JdbcTemplateLockProvider.ColumnNames(
                "name", "lock_until", "locked_at", "locked_by");
        JdbcTemplateLockProvider.Configuration shdlck = JdbcTemplateLockProvider.Configuration.builder()
                .withTableName("shedlock")
                .withJdbcTemplate(new JdbcTemplate(dataSource))
                .usingDbTime() // Works on Postgres, MySQL, MariaDb, MS SQL, Oracle, DB2, HSQL and H2
                .withLockedByValue("suyh")  // 表中locked_by 字段的值，默认是当前主机的主机名。
                .withColumnNames(columnNames)
                .build();
        return new JdbcTemplateLockProvider(shdlck);
    }
}
