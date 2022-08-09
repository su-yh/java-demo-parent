package com.suyh0302.init;

import com.suyh0302.redis.MessagePublisher;
import com.suyh0302.redis.vo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Component
@Slf4j
public class SuyhTestInit implements ApplicationRunner {
    @Resource
    private MessagePublisher redisMessagePublisher;
    @Resource
    private MessageListenerAdapter listener;
    @Resource
    private RedisMessageListenerContainer listenerContainer;

    @Override
    public void run(ApplicationArguments args) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                log.info("publish topic.");
                Student stu = new Student();
                stu.setId(UUID.randomUUID().toString()).setName("name-stu").setGrade(1);
                redisMessagePublisher.publish(Student.getTopic(), stu);
            }
        }, 1000L, 1000L);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("add listener topic.");
                listenerContainer.addMessageListener(listener, new PatternTopic("/redis/publish"));
            }
        }, 20_000L);
    }
}
