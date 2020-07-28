package com.suyh;

import com.suyh.around.service.AroundService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AopApplication.class)
@Slf4j
public class TestAroundAspect {
    @Resource
    private AroundService aroundService;

    @Test
    public void testSayHelloVoid() {
        aroundService.sayHelloVoid();
    }

    @Test
    public void testSayHelloString() {
        aroundService.sayHelloString("message");
    }

    @Test
    public void testGetHelloVoid() {
        String helloVoid = aroundService.getHelloVoid();
        log.info("helloVoid: {}", helloVoid);
    }

    @Test(expected = RuntimeException.class)
    public void testSayHelloException() {
        aroundService.sayHelloException();
    }
}
