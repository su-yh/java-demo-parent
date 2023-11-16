package com.suyh2201.custom.validation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 专门用于集合元素，对集合中的元素进行校验，判断是否存在 null 值的情况。
 *
 * @author suyh
 * @since 2023-11-16
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CollectionElementValidator.class})
public @interface CollectionElement {
    // 是否允许集合的元素存在null 值
    boolean enableNull() default false;

    String message() default "集合元素不允许为null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
