package com.suyh2301;

import com.suyh2301.around.service.SayHelloAroundService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AopApplication.class)
public class TestSayHelloAroundService {
    @Resource
    private SayHelloAroundService service;

    @Test
    public void test01() {
        service.sayHelloVoid();
    }

    @Test
    public void test02() {
        service.sayHelloString("test02");
    }

    @Test
    public void test03() {
        service.sayHelloOther();
    }
}
