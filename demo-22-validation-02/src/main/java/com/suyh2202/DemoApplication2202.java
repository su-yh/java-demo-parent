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
        SpringApplication.run(DemoApplication2202.class, args);
    }


}
