package com.suyh.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务，这些定时任务是在同一个线程中运行的
 * 如果添加了线程池的配置就会在线程池中运行
 */
@Component
public class SchedulerTask2 {
    private Logger logger = LoggerFactory.getLogger(SchedulerTask2.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private int count = 0;

    // 似乎是每5 秒一次
    @Scheduled(cron = "*/5 * * * * ?")
    private void process() {
        logger.info("Task2 this is scheduler task runing  " + (count++));
    }

    // 这个似乎也是每5 秒一次
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        logger.info("Task2 现在时间：" + dateFormat.format(new Date()));
    }
}
