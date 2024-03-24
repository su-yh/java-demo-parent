package com.suyh0304.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * 普通文本redis 缓存
 */
@Slf4j
public class TextCacheRedis extends RedisTemplate<String, String> {
    /**
     * 创建防机器人验证码redis 缓存key
     */
    public static String buildRedisKey(String key) {
        return String.join(":", "suyh", key);
    }

    private final RedisProperties.ClientType clientType;
    private final RedisConnectionFactory factory;

    public TextCacheRedis(RedisConnectionFactory factory, @Nullable RedisProperties.ClientType clientType) {
        this.factory = factory;
        this.clientType = clientType;
    }

    @Override
    public void afterPropertiesSet() {
        super.setKeySerializer(StringRedisSerializer.UTF_8);
        super.setHashKeySerializer(StringRedisSerializer.UTF_8);
        super.setConnectionFactory(factory);
        super.afterPropertiesSet();
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

    public void setText(String key, String value, Duration timeout) {
        super.opsForValue().set(key, value, timeout);
    }

    public String getText(String key) {
        return super.opsForValue().get(key);
    }
}
