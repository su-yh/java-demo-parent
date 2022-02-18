package com.suyh2301;

import com.suyh2301.normal.service.NormalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AopApplication.class)
public class TestNormalAspect {
    @Resource
    private NormalService normalService;

    @Test
    public void testSayHello() {
        normalService.sayHello();
    }
}
