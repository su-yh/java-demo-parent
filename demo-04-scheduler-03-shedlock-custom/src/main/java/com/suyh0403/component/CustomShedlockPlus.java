package com.suyh0403.component;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.ClockProvider;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.LockableRunnable;
import net.javacrumbs.shedlock.core.SimpleLock;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 直接使用{@link LockProvider} 进行动态获取并执行分布式锁的调用。
 * 调用方法没有限制。而且更简单，方便。
 *
 * 参考：{@link net.javacrumbs.shedlock.spring.aop.SpringLockConfigurationExtractor#getLockConfiguration(Runnable)}
 *  以及：{@link LockableRunnable}
 *  还有：{@link ScheduledAnnotationBeanPostProcessor#createRunnable(Object, Method)}
 *  在{@link ScheduledAnnotationBeanPostProcessor#processScheduled(Scheduled, Method, Object)} 中解析了注解{@link @cheduled} 然后注册到了 {@link ScheduledAnnotationBeanPostProcessor#registrar} 中。
 *  主要是按注解中的属性来判断，要注册成为cron 定时任务，还是固定延迟定时任务，又或是固定间隔的定时任务。
 */
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
