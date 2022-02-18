package com.suyh2301;

import com.suyh2301.pointcut.service.PointcutService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AopApplication.class)
public class TestPointcutAspect {
    @Resource
    private PointcutService pointcutService;

    @Test
    public void test01() {
        pointcutService.sayHello();
    }

    // 对异常的切面处理
    @Test
    public void test02() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            pointcutService.execException();
        });

        System.out.println(thrown.getMessage());
    }
}
