package com.suyh2301;

import com.suyh2301.around.service.AroundService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
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

    @Test
    public void testSayHelloException() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            //Code under test
            aroundService.sayHelloException();
        });
    }
}
