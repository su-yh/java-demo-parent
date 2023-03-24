package com.suyh0304.config;

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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.ClientResourcesBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RedisClient.class)
@ConditionalOnProperty(name = "suyh.redis.client-type", havingValue = "lettuce", matchIfMissing = true)
public class SuyhLettuceConfiguration {

    @ConfigurationProperties(prefix = "suyh.redis")
    @Bean(name = "suyhRedisProperties")
    public RedisProperties suyhRedisProperties() {
        return new RedisProperties();
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(ClientResources.class)
    DefaultClientResources lettuceClientResources(ObjectProvider<ClientResourcesBuilderCustomizer> customizers) {
        DefaultClientResources.Builder builder = DefaultClientResources.builder();
        customizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
        return builder.build();
    }

    @Bean
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    LettuceConnectionFactory redisConnectionFactory(
            @Qualifier("suyhRedisProperties") RedisProperties properties,
            ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers,
            ClientResources clientResources) {
        LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(
                builderCustomizers, clientResources, properties);
        return createLettuceConnectionFactory(properties, clientConfig);
    }

    private LettuceConnectionFactory createLettuceConnectionFactory(
            RedisProperties properties,
            LettuceClientConfiguration clientConfiguration) {
        if (getClusterConfiguration(properties) != null) {
            return new LettuceConnectionFactory(getClusterConfiguration(properties), clientConfiguration);
        }
        return new LettuceConnectionFactory(getStandaloneConfig(properties), clientConfiguration);
    }

    protected final RedisClusterConfiguration getClusterConfiguration(RedisProperties properties) {
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

    protected final RedisStandaloneConfiguration getStandaloneConfig(RedisProperties properties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(properties.getHost());
        config.setPort(properties.getPort());
        config.setUsername(properties.getUsername());
        config.setPassword(RedisPassword.of(properties.getPassword()));
        config.setDatabase(properties.getDatabase());
        return config;
    }

    private LettuceClientConfiguration getLettuceClientConfiguration(
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


    private LettuceClientConfiguration.LettuceClientConfigurationBuilder createBuilder(RedisProperties.Pool pool) {
        if (isPoolEnabled(pool)) {
            return new PoolBuilderFactory().createBuilder(pool);
        }
        return LettuceClientConfiguration.builder();
    }

    protected boolean isPoolEnabled(RedisProperties.Pool pool) {
        Boolean enabled = pool.getEnabled();
        return (enabled != null) ? enabled : false;
    }

    private LettuceClientConfiguration.LettuceClientConfigurationBuilder applyProperties(
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
        return builder;
    }

    private ClientOptions createClientOptions(RedisProperties properties) {
        ClientOptions.Builder builder = initializeClientOptionsBuilder(properties);
        Duration connectTimeout = properties.getConnectTimeout();
        if (connectTimeout != null) {
            builder.socketOptions(SocketOptions.builder().connectTimeout(connectTimeout).build());
        }
        return builder.timeoutOptions(TimeoutOptions.enabled()).build();
    }

    private ClientOptions.Builder initializeClientOptionsBuilder(RedisProperties properties) {
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
