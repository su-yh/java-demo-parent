package com.suyh2201.custom.validation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义实现接口参数校验注解
 * 数字匹配
 *
 * @author 苏雲弘
 * @since 2021-03-25
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {NumberMatchValidator.class})
public @interface NumberMatch {

    int[] enabledValue() default { };

    // 这里的{enabledValue} 将会取到实际的可选值。
    String message() default "number not match, options: {enabledValue}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
