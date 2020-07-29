package com.suyh;

import com.suyh.annotation.entity.AnnotationEntity;
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

    private AnnotationEntity entity = new AnnotationEntity();

    @Test
    public void testSayHello() {
        annotationService.sayHello();
    }

    @Test
    public void testSayHelloEntity() {
        entity.sayHelloEntity();
    }
}
