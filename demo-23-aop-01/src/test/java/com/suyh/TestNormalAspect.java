package com.suyh;

import com.suyh.normal.service.NormalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AopApplication.class)
public class TestNormalAspect {
    @Resource
    private NormalService normalService;

    @Test
    public void test01() {
        normalService.sayHello();
    }
}
