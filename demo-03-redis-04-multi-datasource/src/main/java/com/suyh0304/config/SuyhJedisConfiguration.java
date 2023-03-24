package com.suyh0304.config;

import com.suyh0304.redis.TextCacheRedis;
import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RedisClient.class)
public class SuyhJedisConfiguration {

    @ConditionalOnProperty(name = "suyh.redis.business.client-type", havingValue = "jedis", matchIfMissing = false)
    public static class BusinessJedisConfiguration {
        @Bean(name = "businessRedisConnectionFactory")
        public RedisConnectionFactory businessRedisConnectionFactory(
                @Qualifier("businessRedisProperties") RedisProperties properties) {
            return createJedisConnectionFactory(properties);
        }

        @Bean(name = "businessTextCacheRedis")
        public TextCacheRedis businessTextCacheRedis(
                @Qualifier("businessRedisConnectionFactory") RedisConnectionFactory factory,
                @Qualifier("businessRedisProperties") RedisProperties properties) {
            return new TextCacheRedis(factory, properties.getClientType());
        }
    }

    @ConditionalOnProperty(name = "suyh.redis.other.client-type", havingValue = "jedis", matchIfMissing = false)
    public static class OtherJedisConfiguration {
        @Bean(name = "otherRedisConnectionFactory")
        public RedisConnectionFactory otherRedisConnectionFactory(
                @Qualifier("otherRedisProperties") RedisProperties properties) {
            return createJedisConnectionFactory(properties);
        }

        @Bean(name = "otherTextCacheRedis")
        public TextCacheRedis otherTextCacheRedis(
                @Qualifier("otherRedisConnectionFactory") RedisConnectionFactory factory,
                @Qualifier("otherRedisProperties") RedisProperties properties) {
            return new TextCacheRedis(factory, properties.getClientType());
        }
    }

    private static RedisConnectionFactory createJedisConnectionFactory(RedisProperties properties) {
        RedisSentinelConfiguration sentinelConfig = SuyhRedisConfiguration.getSentinelConfig(properties);
        if (sentinelConfig != null) {
            return new JedisConnectionFactory(sentinelConfig);
        }
        RedisClusterConfiguration clusterConfiguration = SuyhRedisConfiguration.getClusterConfiguration(properties);
        if (clusterConfiguration != null) {
            return new JedisConnectionFactory(clusterConfiguration);
        }
        return new JedisConnectionFactory(SuyhRedisConfiguration.getStandaloneConfig(properties));
    }


}
