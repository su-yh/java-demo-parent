package com.suyh;

import com.suyh.annotation.service.AnnotationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AopApplication.class)
public class TestAnnotationAspect {
    @Resource
    private AnnotationService annotationService;

    @Test
    public void testSayHello() {
        annotationService.sayHello();
    }
}
