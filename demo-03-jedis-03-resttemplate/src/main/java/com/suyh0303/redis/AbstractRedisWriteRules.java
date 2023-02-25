package com.suyh0303.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractRedisWriteRules<R, T>
        extends AbstractRedisTemplate<T> {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Value("${spring.profiles.active}")
    private String curEnv;

    public AbstractRedisWriteRules(Class<T> javaType, RedisConnectionFactory factory, RedisProperties redisProperties) {
        super(javaType, factory, redisProperties);
    }

    public void doWriteRule(T wrapperVo, String msg) {
        final String appName = "appName";
        final Long dashboardEnvId = 1L;
        final String key = "redis-key";
        final String ruleName = "ruleName";
        // 有效时间[24, 25) 小时
        final int randomSeconds = SECURE_RANDOM.nextInt(3600);
        long timeout = 24 * 3600L + randomSeconds;

        // 将最新的规则数据写到redis 缓存
        super.opsForValue().set(key, wrapperVo, timeout, TimeUnit.SECONDS);

        ChannelTopic topic = ChannelTopic.of("/" + curEnv + "/" + dashboardEnvId + "/" + ruleName);

        log.debug("redis write rule, app name: {}, dashboard env id: {}, rule name: {}, key: {}, topic: {}",
                appName, dashboardEnvId, ruleName, key, topic.getTopic());

        // 推送消息通知到redis，由其他监听处理。
        // 这个示例里面没有处理监听器的demo
        super.convertAndSend(topic.getTopic(), msg);
    }
}
