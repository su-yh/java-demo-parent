package com.suyh2202;

import com.suyh2202.vo.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class DemoTestSequence {
    public static void main(String[] args) {
        test01();
//        test02();
    }

    private static void test01() {
        User user = new User();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        // 此处指定了校验组是：User.Group.class
        Set<ConstraintViolation<User>> result = validator.validate(user, User.Group.class);

        // 对结果进行遍历输出
        result.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
    }

    private static void test02() {
        User user = new User();
        user.setFirstname("f");
        user.setMiddlename("s");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        // 此处指定了校验组是：User.Group.class
        Set<ConstraintViolation<User>> result = validator.validate(user, User.Group.class);

        // 对结果进行遍历输出
        result.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
    }
}
