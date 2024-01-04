package com.suyh0302.redis;

import com.suyh0302.redis.vo.Student;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.lang.NonNull;

public class RedisMessagePublisher implements MessagePublisher {

    private final RedisTemplate<String, Student> redisTemplate;

    public RedisMessagePublisher(
            RedisTemplate<String, Student> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(@NonNull ChannelTopic topic, @NonNull Student message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
