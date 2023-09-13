package com.suyh2201.custom.validation.constraints;

import com.suyh2201.enums.NodeStatusEnums;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对枚举{@link NodeStatusEnums} 参数的校验，只允许其中几个值的情况时使用。
 * <p>
 *  {@link NodeStatusEnumsMatch @NodeStatusEnumsMatch}(value = {{@link NodeStatusEnums NodeStatusEnums.APPROVED}, {@link NodeStatusEnums NodeStatusEnums.REJECT}})
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NodeStatusEnumsValidator.class)
public @interface NodeStatusEnumsMatch {

    NodeStatusEnums[] value();

    String message() default "可选值：{value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
