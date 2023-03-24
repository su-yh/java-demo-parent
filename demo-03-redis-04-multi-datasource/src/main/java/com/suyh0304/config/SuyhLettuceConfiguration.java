package com.suyh0304.config;

import com.suyh0304.redis.TextCacheRedis;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.ClientResourcesBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RedisClient.class)
public class SuyhLettuceConfiguration {

    @Bean(destroyMethod = "shutdown")
    public DefaultClientResources lettuceClientResources(ObjectProvider<ClientResourcesBuilderCustomizer> customizers) {
        DefaultClientResources.Builder builder = DefaultClientResources.builder();
        customizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
        return builder.build();
    }

    @ConditionalOnProperty(name = "suyh.redis.business.client-type", havingValue = "lettuce", matchIfMissing = false)
    public static class BusinessLettuceConfiguration {
        @Bean(name = "businessRedisConnectionFactory")
        public RedisConnectionFactory businessRedisConnectionFactory(
                @Qualifier("businessRedisProperties") RedisProperties properties,
                ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers,
                ClientResources clientResources) {
            LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(
                    builderCustomizers, clientResources, properties);
            return createLettuceConnectionFactory(properties, clientConfig);
        }

        @Bean(name = "businessTextCacheRedis")
        public TextCacheRedis businessTextCacheRedis(
                @Qualifier("businessRedisConnectionFactory") RedisConnectionFactory factory,
                @Qualifier("businessRedisProperties") RedisProperties properties) {
            return new TextCacheRedis(factory, properties.getClientType());
        }
    }

    @ConditionalOnProperty(name = "suyh.redis.other.client-type", havingValue = "lettuce", matchIfMissing = false)
    public static class OtherLettuceConfiguration {
        @Bean("otherRedisConnectionFactory")
        public RedisConnectionFactory redisConnectionFactory(
                @Qualifier("otherRedisProperties") RedisProperties properties,
                ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers,
                ClientResources clientResources) {
            LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(
                    builderCustomizers, clientResources, properties);
            return createLettuceConnectionFactory(properties, clientConfig);
        }

        @Bean(name = "otherTextCacheRedis")
        public TextCacheRedis otherTextCacheRedis(
                @Qualifier("otherRedisConnectionFactory") RedisConnectionFactory factory,
                @Qualifier("otherRedisProperties") RedisProperties properties) {
            return new TextCacheRedis(factory, properties.getClientType());
        }
    }

    private static RedisConnectionFactory createLettuceConnectionFactory(
            RedisProperties properties,
            LettuceClientConfiguration clientConfiguration) {
        RedisSentinelConfiguration sentinelConfig = SuyhRedisConfiguration.getSentinelConfig(properties);
        if (sentinelConfig != null) {
            return new LettuceConnectionFactory(sentinelConfig, clientConfiguration);
        }
        RedisClusterConfiguration clusterConfiguration = SuyhRedisConfiguration.getClusterConfiguration(properties);
        if (clusterConfiguration != null) {
            return new LettuceConnectionFactory(clusterConfiguration, clientConfiguration);
        }
        return new LettuceConnectionFactory(SuyhRedisConfiguration.getStandaloneConfig(properties), clientConfiguration);
    }

    private static LettuceClientConfiguration getLettuceClientConfiguration(
            ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers,
            ClientResources clientResources, RedisProperties properties) {
        RedisProperties.Pool pool = properties.getLettuce().getPool();
        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = createBuilder(pool);
        applyProperties(properties, builder);
        builder.clientOptions(createClientOptions(properties));
        builder.clientResources(clientResources);
        builderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
        return builder.build();
    }


    private static LettuceClientConfiguration.LettuceClientConfigurationBuilder createBuilder(RedisProperties.Pool pool) {
        if (isPoolEnabled(pool)) {
            return new PoolBuilderFactory().createBuilder(pool);
        }
        return LettuceClientConfiguration.builder();
    }

    protected static boolean isPoolEnabled(RedisProperties.Pool pool) {
        Boolean enabled = pool.getEnabled();
        return (enabled != null) ? enabled : false;
    }

    private static void applyProperties(
            RedisProperties properties, LettuceClientConfiguration.LettuceClientConfigurationBuilder builder) {
        if (properties.isSsl()) {
            builder.useSsl();
        }
        if (properties.getTimeout() != null) {
            builder.commandTimeout(properties.getTimeout());
        }
        if (properties.getLettuce() != null) {
            RedisProperties.Lettuce lettuce = properties.getLettuce();
            if (lettuce.getShutdownTimeout() != null && !lettuce.getShutdownTimeout().isZero()) {
                builder.shutdownTimeout(properties.getLettuce().getShutdownTimeout());
            }
        }
        if (StringUtils.hasText(properties.getClientName())) {
            builder.clientName(properties.getClientName());
        }
    }

    private static ClientOptions createClientOptions(RedisProperties properties) {
        ClientOptions.Builder builder = initializeClientOptionsBuilder(properties);
        Duration connectTimeout = properties.getConnectTimeout();
        if (connectTimeout != null) {
            builder.socketOptions(SocketOptions.builder().connectTimeout(connectTimeout).build());
        }
        return builder.timeoutOptions(TimeoutOptions.enabled()).build();
    }

    private static ClientOptions.Builder initializeClientOptionsBuilder(RedisProperties properties) {
        if (properties.getCluster() != null) {
            ClusterClientOptions.Builder builder = ClusterClientOptions.builder();
            RedisProperties.Lettuce.Cluster.Refresh refreshProperties = properties.getLettuce().getCluster().getRefresh();
            ClusterTopologyRefreshOptions.Builder refreshBuilder = ClusterTopologyRefreshOptions.builder()
                    .dynamicRefreshSources(refreshProperties.isDynamicRefreshSources());
            if (refreshProperties.getPeriod() != null) {
                refreshBuilder.enablePeriodicRefresh(refreshProperties.getPeriod());
            }
            if (refreshProperties.isAdaptive()) {
                refreshBuilder.enableAllAdaptiveRefreshTriggers();
            }
            return builder.topologyRefreshOptions(refreshBuilder.build());
        }
        return ClientOptions.builder();
    }

    /**
     * Inner class to allow optional commons-pool2 dependency.
     */
    private static class PoolBuilderFactory {

        LettuceClientConfiguration.LettuceClientConfigurationBuilder createBuilder(RedisProperties.Pool properties) {
            return LettucePoolingClientConfiguration.builder().poolConfig(getPoolConfig(properties));
        }

        private GenericObjectPoolConfig<?> getPoolConfig(RedisProperties.Pool properties) {
            GenericObjectPoolConfig<?> config = new GenericObjectPoolConfig<>();
            config.setMaxTotal(properties.getMaxActive());
            config.setMaxIdle(properties.getMaxIdle());
            config.setMinIdle(properties.getMinIdle());
            if (properties.getTimeBetweenEvictionRuns() != null) {
                config.setTimeBetweenEvictionRuns(properties.getTimeBetweenEvictionRuns());
            }
            if (properties.getMaxWait() != null) {
                config.setMaxWait(properties.getMaxWait());
            }
            return config;
        }
    }

}
