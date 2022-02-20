package com.suyh0403.component;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.ClockProvider;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.SimpleLock;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
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
        // 持有锁最大时间，如果持有锁的那个实例，一直没释放锁，那么达到该时间之后该锁也会失效。
        Duration lockAtMost = Duration.ofSeconds(5);
        // 持有锁最少时间，就算是主动释放了锁也需要3 秒之后才可以再次被锁。
        Duration lockAtLeast = Duration.ofSeconds(3);
        return new LockConfiguration(ClockProvider.now(), lockName, lockAtMost, lockAtLeast);
    }

    public void task() {
        log.info("执行plus-task 开始.");
        log.info("执行plus-task 结束.");
    }

}
