package com.suyh.customer;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.DefaultLockManager;
import net.javacrumbs.shedlock.core.LockConfigurationExtractor;
import net.javacrumbs.shedlock.core.LockManager;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.SchedulerLock;
import net.javacrumbs.shedlock.spring.internal.SpringLockConfigurationExtractor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Temp implements ApplicationRunner {
    private LockManager lockManager;

    @Resource
    private LockProvider lockProvider;

    public void func() {
        String appName = "xxx";
        LockConfigurationExtractor lockConfigurationExtractor = new SpringLockConfigurationExtractor(
                Duration.ofSeconds(10), Duration.ofSeconds(3), (name) -> appName);
        lockManager = new DefaultLockManager(lockProvider, lockConfigurationExtractor);

    }

    @SchedulerLock
    public void task() {
        log.info("执行task 开始.");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行task 结束.");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        func();
        ScheduledMethodRunnable theTask = new ScheduledMethodRunnable(this, "task");
        Executor executor = Executors.newScheduledThreadPool(10);
        log.info("执行器10 个开始。");
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> lockManager.executeWithLock(theTask));
        }
        log.info("执行器10 个结束。");
    }
}
