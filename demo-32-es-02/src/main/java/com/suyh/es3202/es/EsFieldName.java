package com.suyh.es3202.es;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 范围查询的字段名
 * 这个字段名是在ES 中定义的属性名称，而非Java 中定义的字段名称
 *
 * @author 苏雲弘
 * @since 2020-11-26
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EsFieldName {
    String name();
}
