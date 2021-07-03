package com.suyh0402.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

// 在各自的bean 中添加定时任务
@Component
@Slf4j
public class ScheduleTimerTask implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        // 在这里可以直接添加任务以及cron 表达式
        taskRegistrar.addCronTask(() -> log.info("info - BeansConfiguration"), "* * * * * *");

        // 添加一个普通定时任务，首次执行延迟60 秒，之后每5 秒执行一次
        taskRegistrar.addFixedDelayTask(new IntervalTask(this::someTask, 5000, 20000));

        // 两次任务间隔5 秒，对间隔的解释如下：
        // fixedRate == false: 上次任务<完成>之后开始计时，5秒后开始下一次任务。
        // fixedRate == true:  上次任务<开始>之后开始计时，5秒后开始下一次任务。
        PeriodicTrigger periodicTrigger = new PeriodicTrigger(5, TimeUnit.SECONDS);
        periodicTrigger.setFixedRate(false);
        TriggerTask task = new TriggerTask(this::someTriggerTask, periodicTrigger);
        taskRegistrar.addTriggerTask(task);
    }

    public void someTask() {
        log.info("someTask callback");
    }

    public void someTriggerTask() {
        log.info("TriggerTask");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
