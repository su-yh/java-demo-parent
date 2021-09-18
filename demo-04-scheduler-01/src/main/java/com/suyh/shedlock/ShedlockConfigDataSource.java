package com.suyh.shedlock;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.ScheduledLockConfiguration;
import net.javacrumbs.shedlock.spring.ScheduledLockConfigurationBuilder;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.time.Duration;

@Configuration
@EnableScheduling
// 添加该注释，以启用分布式锁
// 默认该锁持有的最大时间为30秒
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ShedlockConfigDataSource {

    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource);
    }

    // 这个bean 似乎并不是必须的
    @Bean
    public ScheduledLockConfiguration scheduledLockConfiguration(LockProvider lockProvider) {
        // 这里使用了jdbc 的lock provider
        return ScheduledLockConfigurationBuilder
                .withLockProvider(lockProvider)
                .withPoolSize(10)
                // 默认最大的lock expire 时间为10分钟
                .withDefaultLockAtMostFor(Duration.ofMinutes(10))
                .build();
    }
}
