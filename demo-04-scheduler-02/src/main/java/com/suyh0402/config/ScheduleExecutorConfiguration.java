package com.suyh0402.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

// 不要遗漏注解 @EnableScheduling，否则定时任务将不生效。
@Configuration
@EnableScheduling
public class ScheduleExecutorConfiguration implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 在这里添加统一的任务调执行器吧
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("suyh-demo-pool-%d").build();
        Executor executor = Executors.newScheduledThreadPool(5, factory);
        // 设定一个长度固定的定时任务线程池
        taskRegistrar.setScheduler(executor);
    }
}
