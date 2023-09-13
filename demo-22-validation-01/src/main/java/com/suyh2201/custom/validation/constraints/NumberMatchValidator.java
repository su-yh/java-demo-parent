package com.suyh2201.custom.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义实现接口参数校验注解对数字匹配的功能实现
 * 允许null值，如果非null 必须匹配指定的值
 *
 * @author 苏雲弘
 * @since 2021-03-25
 */
public class NumberMatchValidator implements ConstraintValidator<NumberMatch, Number> {
    private int[] enableValues;

    @Override
    public void initialize(NumberMatch constraintAnnotation) {
        enableValues = constraintAnnotation.enabledValue();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        for (int enableValue : enableValues) {
            if (value.equals(enableValue)) {
                return true;
            }
        }

        return false;
    }
}
