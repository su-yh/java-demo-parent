package com.suyh0302.redis;

import com.suyh0302.redis.vo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestMachineWriteRedis extends AbstractRedisTemplate<Student> {
    public TestMachineWriteRedis(RedisConnectionFactory factory) {
        super(Student.class, factory);
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }
}
