package com.suyh.shedlock;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式定时任务锁机制
 * 要使用 mysql 做分布式定时任务，需要创建一张表供其使用
 * CREATE TABLE shedlock(
 * name VARCHAR(64),
 * lock_until TIMESTAMP(3) NULL,
 * locked_at TIMESTAMP(3) NULL,
 * locked_by  VARCHAR(255),
 * PRIMARY KEY (name)
 * );
 */
@Component
@Slf4j
public class ScheduleShedlock {

    /**
     * name:
     *      分布式锁的名称，相同名称的锁，将会相互抢一把锁
     * lockAtMostFor:
     *      指定该锁最大有效时间，即使没有主动释放该锁一样失效。
     * lockAtLeastFor:
     *      指定该锁最小有效时间，即使主动释放在该段时间内也不能再次使用该锁
     */
    @Scheduled(cron = "*/10 * * * * ?")
    @SchedulerLock(name = "channelCronName", lockAtLeastFor = "PT3S", lockAtMostFor = "PT5S")
    public void channelCron() {
        log.info("*********从整秒开始，每经过10 秒执行一次，最少持有该锁3 秒，最多持有该锁5 秒");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
