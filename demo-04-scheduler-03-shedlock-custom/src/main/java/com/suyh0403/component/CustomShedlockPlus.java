package com.suyh0403.component;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.SimpleLock;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class CustomShedlockPlus {
    @Resource
    private LockProvider lockProvider;

    private final Map<String, LockConfiguration> appNameLock = new ConcurrentHashMap<>();

    /**
     * 为每一个app name 做注册
     * 添加分布式锁，在同一时刻只允许一个成功。
     *
     * @param appName app name
     */
    public boolean executeRegistryWithLock(@NonNull String appName) {
        LockConfiguration lockConfig = appNameLock.computeIfAbsent(appName, this::buildLockConfiguration);
        Optional<SimpleLock> lock = lockProvider.lock(lockConfig);
        if (!lock.isPresent()) {
            log.debug("Not executing {}. It's locked.", lockConfig.getName());
            return false;
        }

        // 获取到锁，执行任务
        try {
            this.task();
            return true;
        } finally {
            lock.get().unlock();
        }
    }

    private LockConfiguration buildLockConfiguration(String lockName) {
        Duration lockAtMost = Duration.ofSeconds(10);   // 持有锁最大时间
        Duration lockAtLeast = Duration.ofSeconds(3);   // 持有锁最少时间
        Instant now = Instant.now();
        return new LockConfiguration(lockName, now.plus(lockAtMost), now.plus(lockAtLeast));
    }

    public void task() {
        log.info("执行plus-task 开始.");
        log.info("执行plus-task 结束.");
    }

}
