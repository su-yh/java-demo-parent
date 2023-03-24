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
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RedisClient.class)
public class SuyhJedisConfiguration {
    @ConditionalOnProperty(name = "suyh.redis.business.client-type", havingValue = "jedis", matchIfMissing = false)
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

    private static RedisConnectionFactory createJedisConnectionFactory(RedisProperties properties) {
        RedisClusterConfiguration clusterConfiguration = getClusterConfiguration(properties);
        if (clusterConfiguration != null) {
            return new JedisConnectionFactory(clusterConfiguration);
        }
        return new JedisConnectionFactory(getStandaloneConfig(properties));
    }

    protected static RedisClusterConfiguration getClusterConfiguration(RedisProperties properties) {
        if (properties.getCluster() == null) {
            return null;
        }
        RedisProperties.Cluster clusterProperties = properties.getCluster();
        RedisClusterConfiguration config = new RedisClusterConfiguration(clusterProperties.getNodes());
        if (clusterProperties.getMaxRedirects() != null) {
            config.setMaxRedirects(clusterProperties.getMaxRedirects());
        }
        config.setUsername(properties.getUsername());
        if (properties.getPassword() != null) {
            config.setPassword(RedisPassword.of(properties.getPassword()));
        }
        return config;
    }

    protected static RedisStandaloneConfiguration getStandaloneConfig(RedisProperties properties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(properties.getHost());
        config.setPort(properties.getPort());
        config.setUsername(properties.getUsername());
        config.setPassword(RedisPassword.of(properties.getPassword()));
        config.setDatabase(properties.getDatabase());
        return config;
    }



//
//    @Bean(destroyMethod = "shutdown")
//    public DefaultClientResources lettuceClientResources(ObjectProvider<ClientResourcesBuilderCustomizer> customizers) {
//        DefaultClientResources.Builder builder = DefaultClientResources.builder();
//        customizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
//        return builder.build();
//    }
//
//    @ConditionalOnProperty(name = "suyh.redis.business.client-type", havingValue = "lettuce", matchIfMissing = true)
//    public static class BusinessLettuceConfiguration {
//        @Bean(name = "businessRedisConnectionFactory")
//        public LettuceConnectionFactory redisConnectionFactory(
//                @Qualifier("businessRedisProperties") RedisProperties properties,
//                ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers,
//                ClientResources clientResources) {
//            LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(
//                    builderCustomizers, clientResources, properties);
//            return createLettuceConnectionFactory(properties, clientConfig);
//        }
//
//        @Bean(name = "businessTextCacheRedis")
//        public TextCacheRedis businessTextCacheRedis(
//                @Qualifier("businessRedisConnectionFactory") LettuceConnectionFactory factory,
//                @Qualifier("businessRedisProperties") RedisProperties properties) {
//            return new TextCacheRedis(factory, properties.getClientType());
//        }
//    }
//
//    @ConditionalOnProperty(name = "suyh.redis.other.client-type", havingValue = "lettuce", matchIfMissing = true)
//    public static class OtherLettuceConfiguration {
//        @Bean("otherRedisConnectionFactory")
//        public LettuceConnectionFactory redisConnectionFactory(
//                @Qualifier("otherRedisProperties") RedisProperties properties,
//                ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers,
//                ClientResources clientResources) {
//            LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(
//                    builderCustomizers, clientResources, properties);
//            return createLettuceConnectionFactory(properties, clientConfig);
//        }
//
//        @Bean(name = "otherTextCacheRedis")
//        public TextCacheRedis otherTextCacheRedis(
//                @Qualifier("otherRedisConnectionFactory") LettuceConnectionFactory factory,
//                @Qualifier("otherRedisProperties") RedisProperties properties) {
//            return new TextCacheRedis(factory, properties.getClientType());
//        }
//    }
}
