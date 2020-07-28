package com.suyh;

import com.suyh.pointcut.service.PointcutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AopApplication.class)
public class TestPointcutAspect {
    @Resource
    private PointcutService pointcutService;

    @Test
    public void test01() {
        pointcutService.sayHello();
    }

    // 对异常的切面处理
    @Test(expected = RuntimeException.class)
    public void test02() {
        pointcutService.execException();
    }
}
