package com.suyh2301;

import com.suyh2301.annotation.entity.AnnotationEntity;
import com.suyh2301.annotation.service.AnnotationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
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
