package com.suyh0403.component;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.DefaultLockManager;
import net.javacrumbs.shedlock.core.LockConfigurationExtractor;
import net.javacrumbs.shedlock.core.LockManager;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.SchedulerLock;
import net.javacrumbs.shedlock.spring.internal.SpringLockConfigurationExtractor;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CustomShedlock {
    @Resource
    private LockProvider lockProvider;

    private final Map<String, LockManager> appNameLock = new ConcurrentHashMap<>();

    /**
     * 为每一个app name 做注册
     * 添加分布式锁，在同一时刻只允许一个成功。
     *
     * @param appName app name
     */
    public void executeRegistry(@NonNull String appName) throws NoSuchMethodException {
        LockManager lockManager = appNameLock.computeIfAbsent(appName, this::buildLockManager);
        ScheduledMethodRunnable theTask = new ScheduledMethodRunnable(this, "task");
        lockManager.executeWithLock(theTask);
    }

    private LockManager buildLockManager(String appName) {
        Duration lockAtMost = Duration.ofSeconds(10);   // 持有锁最大时间
        Duration lockAtLeast = Duration.ofSeconds(3);   // 持有锁最少时间
        // 字符串解析器，这里不做任何别的处理，直接给定。
        // 它其实要用这个名称来处理全局唯一
        StringValueResolver resolver = (name) -> appName;
        LockConfigurationExtractor lockConfigurationExtractor = new SpringLockConfigurationExtractor(
                lockAtMost, lockAtLeast, resolver);
        return new DefaultLockManager(lockProvider, lockConfigurationExtractor);
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
}
