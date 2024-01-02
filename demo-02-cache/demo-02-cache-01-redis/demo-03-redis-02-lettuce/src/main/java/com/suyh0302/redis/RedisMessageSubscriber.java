package com.suyh0302.redis;

import com.suyh0302.redis.vo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class RedisMessageSubscriber implements MessageListener {

    @Resource
    private Jackson2JsonRedisSerializer<Student> serializer;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        final Student student = serializer.deserialize(message.getBody());
        log.info("Message received: {}", student);
    }
}
