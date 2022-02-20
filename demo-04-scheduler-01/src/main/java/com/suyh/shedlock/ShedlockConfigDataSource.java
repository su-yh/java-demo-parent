package com.suyh.shedlock;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
// 添加该注释，以启用分布式锁
// 默认该锁持有的最大时间为30秒
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ShedlockConfigDataSource {

    @ConditionalOnMissingBean(LockProvider.class)
    @Bean
    public LockProvider lockProvider3(DataSource dataSource) {
        JdbcTemplateLockProvider.Configuration shdlck = JdbcTemplateLockProvider.Configuration.builder()
                .withTableName("shedlock")
                .withJdbcTemplate(new JdbcTemplate(dataSource))
                .usingDbTime() // Works on Postgres, MySQL, MariaDb, MS SQL, Oracle, DB2, HSQL and H2
                .withLockedByValue("suyh")  // 表中locked_by 字段的值，默认是当前主机的主机名。
                .build();
        return new JdbcTemplateLockProvider(shdlck);
    }
}
