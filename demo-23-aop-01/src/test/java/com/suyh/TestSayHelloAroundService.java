package com.suyh;

import com.suyh.around.service.SayHelloAroundService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
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
