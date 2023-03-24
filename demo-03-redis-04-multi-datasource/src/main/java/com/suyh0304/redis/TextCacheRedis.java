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
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * 普通文本redis 缓存
 */
@Component
@Slf4j
public class TextCacheRedis extends RedisTemplate<String, String> {
    public static final Duration VERIFY_CODE_EXPIRE = Duration.ofMinutes(5L);
    public static final Duration CAPTCHA_EXPIRE = Duration.ofMinutes(3L);

    /**
     * 创建防机器人验证码redis 缓存key
     */
    public static String buildCaptchaRedisKey(String uuid) {
        return String.join(":", "suyh", "captcha", "uuid", uuid);
    }

    /**
     * 创建手机校验码redis 缓存key
     */
    public static String buildPhoneRedisKey(String phone) {
        return String.join(":", "suyh", "user", "phone", phone);
    }

    /**
     * 创建email 校验码redis 缓存key
     */
    public static String buildEmailRedisKey(String email) {
        return String.join(":", "suyh", "user", "email", email);
    }

    private final RedisProperties redisProperties;
    private final RedisConnectionFactory factory;

    public TextCacheRedis(RedisConnectionFactory factory, RedisProperties redisProperties) {
        this.factory = factory;
        this.redisProperties = redisProperties;
    }

    @Override
    public void afterPropertiesSet() {
        final StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        super.setKeySerializer(stringRedisSerializer);
        super.setHashKeySerializer(stringRedisSerializer);
        super.setConnectionFactory(factory);
        super.afterPropertiesSet();
    }

    @Override
    public Set<String> keys(@NonNull String pattern) {
        if (RedisProperties.ClientType.LETTUCE.equals(redisProperties.getClientType())) {
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
