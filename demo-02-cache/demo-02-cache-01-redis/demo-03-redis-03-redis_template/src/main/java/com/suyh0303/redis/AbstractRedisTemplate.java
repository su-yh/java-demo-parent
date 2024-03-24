package com.suyh0303.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyh0303.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public abstract class AbstractRedisTemplate<T> extends RedisTemplate<String, T> {
    private final RedisConnectionFactory factory;
    private final Jackson2JsonRedisSerializer<T> serializer;
    private final RedisProperties redisProperties;

    public AbstractRedisTemplate(Class<T> javaType, RedisConnectionFactory factory, RedisProperties redisProperties) {
        this.factory = factory;
        serializer = new Jackson2JsonRedisSerializer<>(javaType);
        this.redisProperties = redisProperties;
    }

    @Override
    public void afterPropertiesSet() {
        ObjectMapper mapper = new ObjectMapper();
        JsonUtil.initMapper(mapper);
        serializer.setObjectMapper(mapper);

        super.setKeySerializer(StringRedisSerializer.UTF_8);
        super.setHashKeySerializer(StringRedisSerializer.UTF_8);
        super.setValueSerializer(serializer);
        super.setHashValueSerializer(serializer);
        super.setConnectionFactory(factory);
        super.afterPropertiesSet();
    }

    public T deserialize(@Nullable byte[] bytes) {
        return serializer.deserialize(bytes);
    }

    @Override
    public Set<String> keys(@NonNull String pattern) {
        if (redisProperties.getClientType().equals(RedisProperties.ClientType.LETTUCE)) {
            Set<String> keys = new HashSet<>();
            RedisConnection connection = factory.getConnection();
            ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).count(1000L).build();
            Cursor<byte[]> cursor = connection.scan(scanOptions);
            while (cursor.hasNext()) {
                keys.add(new String(cursor.next()));
            }

            return keys;
        }

        return super.keys(pattern);
    }
}
