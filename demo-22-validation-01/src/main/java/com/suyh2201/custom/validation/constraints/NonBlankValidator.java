package com.suyh2201.custom.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义实现接口参数校验注解对字符串的功能实现
 * 允许null值，但是不允许元素空
 *
 * @author 苏雲弘
 * @since 2021-03-25
 */
public class NonBlankValidator implements ConstraintValidator<NonBlank, CharSequence> {
    @Override
    public void initialize(NonBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.toString().trim().length() > 0;
    }
}
