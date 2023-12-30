package com.suyh0302.redis;

import com.suyh0302.redis.vo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@DependsOn("testMachineWriteRedis")
@Component
@Slf4j
public class TestMachineReadRedis extends AbstractRedisTemplate<Student> {
    @Value("${spring.profiles.active}")
    private String curEnv;

    public TestMachineReadRedis(RedisConnectionFactory factory) {
        super(Student.class, factory);
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();

        super.opsForValue().get(curEnv + ":suyh-appname:157:machines");
        final Set<String> keys = super.keys(curEnv + ":suyh-appname:15*:machines:*");
        System.out.println("keys: " + keys);
        if (keys == null) {
            return;
        }
        final List<Student> machines = super.opsForValue().multiGet(keys);
    }
}
