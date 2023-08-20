package com.suyh5801.util;

import org.springframework.lang.NonNull;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 对于参数校验的工具类
 */
public class SuyhValidationUtils {
    public static <T> void validate(@NonNull Validator validator, @NonNull T obj, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj, groups);
        if (constraintViolations.size() > 0) {
            StringBuilder validateError = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                validateError.append(constraintViolation.getPropertyPath()).append(" ")
                        .append(constraintViolation.getMessage()).append("; ");
            }

            throw new RuntimeException(validateError.toString());
        }
    }
}
