package com.suyh.shedlock;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * TODO: 但是这个我还没有试着支行过
 *
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
@EnableScheduling
@Slf4j
public class ScheduleShedlock {

    /**
     * name:
     *      分布式锁的名称，相同名称的锁，将会相互抢一把锁
     * lockAtMostFor & lockAtMostForString:
     *      lockAtMostFor 单位 毫秒
     *      lockAtMostForString 使用"PT14M" 意味着它将被锁定不超过14分钟。
     *
     *      指定在执行节点死亡时应将锁保留多长时间。
     *      这只是一个备用选项，在正常情况下，任务完成后立即释放锁定。
     *      您必须将其设置lockAtMostFor为比正常执行时间长得多的值。
     *      如果任务花费的时间超过 lockAtMostFor了所导致的行为，则可能无法预测（更多的进程将有效地持有该锁）。
     *      - 也就是说如果锁没有正常释放，那么到这个时间之后这个锁也会自动无效(释放)
     * lockAtLeastFor & lockAtLeastForString:
     *      指定应保留锁定的最短时间。可以防止任务很短且节点之间的时钟差的情况下，多节点执行。
     */
    @Scheduled(cron = "0 0 * * * ? ")
    @SchedulerLock(name = "channelCronName", lockAtMostFor = 5 * 1000)
    public void channelCron() {
        log.info("*********每小时执行一次");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
