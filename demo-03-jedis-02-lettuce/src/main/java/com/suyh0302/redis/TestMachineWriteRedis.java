package com.suyh0302.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
public class TestMachineWriteRedis extends AbstractRedisTemplate<MachineInfo> {
    @Value("${spring.profiles.active}")
    private String curEnv;

    public TestMachineWriteRedis(RedisConnectionFactory factory) {
        super(MachineInfo.class, factory);
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();

        MachineInfo machineInfo1 = new MachineInfo();
        machineInfo1.setUuid("6dccefdc22e34629af201e1847df7451");
        MachineInfo machineInfo2 = new MachineInfo();
        machineInfo2.setUuid("9b32077caafa440fb741332b13b188cf");

        super.opsForValue().set(curEnv + ":suyh-appname:157:machines:" + machineInfo1.getUuid(), machineInfo1, Duration.ofSeconds(60));
        super.opsForValue().set(curEnv + ":suyh-appname:157:machines:" + machineInfo2.getUuid(), machineInfo2, Duration.ofSeconds(60));
        super.opsForValue().set(curEnv + ":suyh-appname:155:machines:" + machineInfo2.getUuid(), machineInfo2, Duration.ofSeconds(60));
        super.opsForValue().set(curEnv + ":suyh-appname:153:machines:" + machineInfo2.getUuid(), machineInfo2, Duration.ofSeconds(60));
    }
}
