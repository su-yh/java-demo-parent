package com.suyh0308.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
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

    @AliasFor(annotation = Cacheable.class)
    String cacheResolver() default "";

    @AliasFor(annotation = Cacheable.class)
    String condition() default "";

    @AliasFor(annotation = Cacheable.class)
    String unless() default "";

    @AliasFor(annotation = Cacheable.class)
    boolean sync() default false;
}
