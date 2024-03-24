package com.suyh0302.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;

@Slf4j
public abstract class AbstractRedisTemplate<T> extends RedisTemplate<String, T> {
    private final RedisConnectionFactory factory;
    private final Jackson2JsonRedisSerializer<T> serializer;

    public AbstractRedisTemplate(Class<T> javaType, RedisConnectionFactory factory) {
        this.factory = factory;
        serializer = new Jackson2JsonRedisSerializer<>(javaType);
    }

    @Override
    public void afterPropertiesSet() {
        ObjectMapper mapper = new ObjectMapper();
//        JsonUtil.initMapper(mapper);

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

}
