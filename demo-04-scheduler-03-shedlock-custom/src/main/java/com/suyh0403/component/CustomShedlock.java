package com.suyh0403.component;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.ClockProvider;
import net.javacrumbs.shedlock.core.DefaultLockManager;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockConfigurationExtractor;
import net.javacrumbs.shedlock.core.LockManager;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.spring.ExtendedLockConfigurationExtractor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 借助{@link DefaultLockManager} 的实现，动态获取并执行分布式锁的调用。
 * 但是仅支持{@link Runnable} 接口的方法。
 */
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
    public void executeRegistryWithLock(@NonNull String appName) {
        LockManager lockManager = appNameLock.computeIfAbsent(appName, this::buildLockManager);
        // 原始的调用对runnable 的要求还挺多的。所以需要传入ScheduledMethodRunnable 类型的对象才合适。
        // 但是现在我这里重写了SpringLockConfigurationExtractor#getLockConfiguration 方法，就没有这些限制了。
        // ScheduledMethodRunnable theTask = new ScheduledMethodRunnable(this, "task");
        lockManager.executeWithLock(this::task);
    }

    private LockManager buildLockManager(String appName) {
        Duration lockAtMostFor = Duration.ofSeconds(10);   // 持有锁最大时间
        Duration lockAtLeastFor = Duration.ofSeconds(3);   // 持有锁最少时间
        LockConfigurationExtractor lockConfigurationExtractor
                = new CustomLockConfigurationExtractor(
                lockAtMostFor, lockAtLeastFor, appName);
        return new DefaultLockManager(lockProvider, lockConfigurationExtractor);
    }

    public void task() {
        log.info("执行base-task 开始.");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行base-task 结束.");
    }

    /**
     * 这里自定义这个扩展类，主要是重写{@link #getLockConfiguration} 方法，在父类里面对task 做了一些要求。
     * 为了方便，这里不做太多限制，方便使用。
     */
    private static class CustomLockConfigurationExtractor implements ExtendedLockConfigurationExtractor {
        private final Duration lockAtMostFor;
        private final Duration lockAtLeastFor;
        private final String lockName;

        public CustomLockConfigurationExtractor(
                @NonNull Duration lockAtMostFor,
                @NonNull Duration lockAtLeastFor,
                @NonNull String lockName) {
            // StringValueResolver 值直接置null。简化string 的解析，所有的字符串在这里都直接通过lockName 以及TemporalAmount 处理
            // super(lockAtMostFor, lockAtLeastFor, null);

            this.lockAtMostFor = lockAtMostFor;
            this.lockAtLeastFor = lockAtLeastFor;
            this.lockName = lockName;
        }

        @Override
        @NonNull
        public Optional<LockConfiguration> getLockConfiguration(@NonNull Runnable task) {
            LockConfiguration lockConfiguration = new LockConfiguration(
                    ClockProvider.now(), lockName, lockAtMostFor, lockAtLeastFor);
            return Optional.of(lockConfiguration);
        }

        @Override
        @NonNull
        public Optional<LockConfiguration> getLockConfiguration(Object object, Method method) {
            LockConfiguration lockConfiguration = new LockConfiguration(
                    ClockProvider.now(), lockName, lockAtMostFor, lockAtLeastFor);
            return Optional.of(lockConfiguration);
        }
    }
}
