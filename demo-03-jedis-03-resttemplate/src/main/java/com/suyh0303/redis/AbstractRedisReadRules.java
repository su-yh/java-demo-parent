package com.suyh0303.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Slf4j
public abstract class AbstractRedisReadRules<T> extends AbstractRedisTemplate<T> {
    public AbstractRedisReadRules(Class<T> javaType, RedisConnectionFactory factory, RedisProperties redisProperties) {
        super(javaType, factory, redisProperties);
    }

    public T readRule() {
        final String key = "redis-key";
        return super.opsForValue().get(key);
    }
}
