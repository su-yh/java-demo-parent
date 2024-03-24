package com.suyh0310.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 扩展RedisCacheManager 的builder
 * 但是这个是来源是 org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration#cacheManager(org.springframework.boot.autoconfigure.cache.CacheProperties, org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers, org.springframework.beans.factory.ObjectProvider, org.springframework.beans.factory.ObjectProvider, org.springframework.data.redis.connection.RedisConnectionFactory, org.springframework.core.io.ResourceLoader)
 * 如果自定义了CacheManager 则不会走这个自定义的实现
 * 同时也需要将该对象添加到spring 的bean 缓存中，让spring 来进行管理。
 * RedisCacheConfiguration 是通过bean 对象的方式来获取该自定义配置的。
 * <p>
 * 所以整体来说，这个是spring redis 提供的扩展，使用它更好。
 * 使用这里就不要使用CacheManager 的自定义实现了。
 * <p>
 * 参考源码：{@link org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration}
 *
 * @author suyh
 * @since 2023-11-13
 */
@Slf4j
public class SuyhRedisCacheManagerBuilderCustomizer implements RedisCacheManagerBuilderCustomizer {
    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
        // 不建议在这里处理config ，因为这里处理了，那么配置文件中的配置将会被忽略掉了。
        /**
         * 这里的代码配置({@link RedisCacheConfiguration )，可以对应到 {@link CacheProperties.Redis} 然后在配置文件中添加
         * 对应的配置类参考 {@link CacheProperties}。
         * 但是这里却拿不到对应的bean 对象，估计是bean 对象扫描的优先级问题。
         */
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存默认的有效时间，全局控制注解 @Cacheable 的默认过期时间。
                // .entryTtl(Duration.ofSeconds(30))
                // 这里禁止了null 值，方法如果返回了null，则会抛异常，可以在方法上添加 @Cacheable(unless="#result == null")
                .disableCachingNullValues();
        builder.cacheDefaults(redisCacheConfiguration);
        try {
            Field cacheWriter = RedisCacheManager.RedisCacheManagerBuilder.class.getField("cacheWriter");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        // TODO: suyh - 这里的序列化有问题，还没能解决。
        //  如何同时满足String 和实体对象的序列化 与反序列化的问题。
        //  如果是对象的反序列化，我们可以使用一个接口让它们都实现这个接口，但是String 就没办法了呀？
        configRedisSerializer(builder);
    }

    /**
     * 由于{@link org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration} 中没有给序列化配置的空间，这里暂时只能通过反射来强硬添加序列化的配置。
     * 主要不想用jdk 的那个序列化方式。
     *
     * 参考代码：{@link org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration#createConfiguration(CacheProperties, ClassLoader)}
     * 		config = config.serializeValuesWith(
     * 				SerializationPair.fromSerializer(new JdkSerializationRedisSerializer(classLoader)));
     */
    private void configRedisSerializer(RedisCacheManager.RedisCacheManagerBuilder builder) {
        RedisCacheConfiguration defaultCacheConfiguration = null;
        try {
            Field defaultCacheConfigurationField = ReflectionUtils.findField(
                    RedisCacheManager.RedisCacheManagerBuilder.class, "defaultCacheConfiguration", RedisCacheConfiguration.class);
            ReflectionUtils.makeAccessible(defaultCacheConfigurationField);
            defaultCacheConfiguration = (RedisCacheConfiguration) defaultCacheConfigurationField.get(builder);

        } catch (IllegalAccessException exception) {
            log.error("some exception.", exception);
        }

        if (defaultCacheConfiguration == null) {
            defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        }
        RedisCacheConfiguration redisCacheConfiguration = defaultCacheConfiguration.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer(Object.class)));
        builder.cacheDefaults(redisCacheConfiguration);
    }
}
