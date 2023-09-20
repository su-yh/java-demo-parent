package com.suyh0302.init;

import com.suyh0302.redis.MessagePublisher;
import com.suyh0302.redis.TestMachineWriteRedis;
import com.suyh0302.redis.vo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
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
    @Resource
    private TestMachineWriteRedis testMachineWriteRedis;

    @Override
    public void run(ApplicationArguments args) {
        Student student = new Student();
        student.setId("suyh-test-id");
        student.setGrade(2);
        student.setName("suyh");
        testMachineWriteRedis.opsForValue().set("suyh-test-local-host", student, Duration.ofMinutes(3L));

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
        // 动态添加订阅，5 秒后再订阅
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("add listener topic.");
                // 前缀订阅：new PatternTopic("/redis/*")
                // PatternTopic.of("/**")
                // PatternTopic.of("/*/publish")
                // PatternTopic.of("/redis/*")
                // 验证了一下，好像只能得到最新的推送，如果是在启动之前的推送是获取不到的。
                listenerContainer.addMessageListener(listener, new PatternTopic("/redis/publish"));
            }
        }, 5_000L);
    }
}
