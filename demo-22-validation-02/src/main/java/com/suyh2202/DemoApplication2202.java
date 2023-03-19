package com.suyh2202;

import com.suyh2202.vo.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;

// 参考博客: https://www.cnblogs.com/yourbatman/p/11387438.html
@SpringBootApplication
public class DemoApplication2202 {
    public static void main(String[] args)  {
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
