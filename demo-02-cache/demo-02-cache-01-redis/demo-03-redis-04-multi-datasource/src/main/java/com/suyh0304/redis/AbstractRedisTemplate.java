package com.suyh0304.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final RedisProperties.ClientType clientType;
    private final ObjectMapper objectMapper;

    public AbstractRedisTemplate(
            Class<T> javaType, ObjectMapper objectMapper, RedisConnectionFactory factory,
            @Nullable RedisProperties.ClientType clientType) {
        this.factory = factory;
        this.objectMapper = objectMapper;
        serializer = new Jackson2JsonRedisSerializer<>(javaType);
        this.clientType = clientType;
    }

    @Override
    public void afterPropertiesSet() {
        serializer.setObjectMapper(objectMapper);

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
        if (RedisProperties.ClientType.LETTUCE.equals(clientType)) {
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
