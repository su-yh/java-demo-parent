package com.suyh2202;

import com.suyh2202.vo.Person;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;

public class DemoTest {
    public static void main(String[] args) {
        test(25);
        test(35);
    }

    private static void test(int age) {
        Person person = new Person();
        person.setName("fsx");
        person.setAge(age);
        person.setHobbies(Arrays.asList("足球","篮球"));

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // 对结果进行遍历输出
        result.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
    }
}
