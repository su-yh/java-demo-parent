package com.suyh0308.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Duration;

/**
 * 继承自@Cacheable ，这里主要是添加了一个redis 的缓存有效时间
 *
 * @author suyh
 * @since 2023-11-13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Cacheable
public @interface SuyhCacheable {
    @AliasFor(annotation = Cacheable.class, attribute = "value")
    String[] value() default {};

    @AliasFor(annotation = Cacheable.class, attribute = "cacheNames")
    String[] cacheNames() default {};

    @AliasFor(annotation = Cacheable.class)
    String key() default "";

    @AliasFor(annotation = Cacheable.class)
    String keyGenerator() default "";

    @AliasFor(annotation = Cacheable.class)
    String cacheManager() default "";

    /**
     * 这里添加的是自定义的解析器，使用当前这个注解一般都是要指定一个缓存时间，所以这里指定我自定义的解析器。
     * 不过也没有关系，这个解析器支持原始的解析功能。
     */
    @AliasFor(annotation = Cacheable.class)
    String cacheResolver() default "suyhCacheResolver";

    @AliasFor(annotation = Cacheable.class)
    String condition() default "";

    @AliasFor(annotation = Cacheable.class)
    String unless() default "";

    @AliasFor(annotation = Cacheable.class)
    boolean sync() default false;

    /**
     * 额外添加的注解属性参数，用来指定缓存的有效时间，这里针对的就是Redis
     * 这里的字符串，需要满足 {@link Duration#parse(CharSequence)} 解析格式
     */
    String timeToLive() default "";
}
