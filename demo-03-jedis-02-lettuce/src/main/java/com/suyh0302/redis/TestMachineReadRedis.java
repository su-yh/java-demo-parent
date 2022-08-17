package com.suyh0302.redis;

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
public class TestMachineReadRedis extends AbstractRedisTemplate<MachineInfo> {
    @Value("${spring.profiles.active}")
    private String curEnv;

    public TestMachineReadRedis(RedisConnectionFactory factory) {
        super(MachineInfo.class, factory);
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
        final List<MachineInfo> machines = super.opsForValue().multiGet(keys);
        System.out.println("mechines: " + JsonUtils.showJsonValue(machines));
    }
}
