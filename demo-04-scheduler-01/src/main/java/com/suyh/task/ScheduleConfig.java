package com.suyh.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

// 所有的定时任务都放在一个线程池中，定时任务启动时使用不同都线程。

/**
 * 所有的定时任务都会到这个线程池中来取线程，然后运行定时任务。
 * 如果不加这个配置类，则所有的定时任务都会在一个线程中运行。
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 设定一个长度10的定时任务线程池
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
        // 在这里可以直接添加任务以及cron 表达式
        taskRegistrar.addCronTask(() -> log.info("info - BeansConfiguration"), "* * * * * *");
        // 添加一个普通定时任务，首次执行延迟60 秒，之后每5 秒执行一次
        taskRegistrar.addFixedDelayTask(
                new IntervalTask(this::someTask, 5000, 60000)
        );

        PeriodicTrigger periodicTrigger = new PeriodicTrigger(5000);
        // fixedRate == false: 上次任务<完成>之后开始计时，5秒后开始下一次任务。
        // fixedRate == true:  上次任务<开始>之后开始计时，5秒后开始下一次任务。
        periodicTrigger.setFixedRate(true);
        TriggerTask task = new TriggerTask(() -> {
            log.info("TriggerTask");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, periodicTrigger);
        taskRegistrar.addTriggerTask(task);
    }
}
