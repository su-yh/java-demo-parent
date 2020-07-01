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
    }
}
