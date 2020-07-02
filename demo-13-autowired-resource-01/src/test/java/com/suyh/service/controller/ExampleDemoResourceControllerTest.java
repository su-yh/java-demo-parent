package com.suyh.service.controller;


import com.suyh.ResourceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = ResourceApplication.class)
public class ExampleDemoResourceControllerTest {
    private static Logger logger = LoggerFactory.getLogger(ExampleDemoResourceControllerTest.class);

    @Autowired
    private ExampleDemoResourceController resourceController;

    @Test
    public void getNameResource01() {
        String autowiredName = resourceController.getNameResource01();
        logger.info("test, getNameResource01 result: " + autowiredName);
    }

    @Test
    public void getNameResource02() {
        String autowiredName = resourceController.getNameResource02();

        logger.info("test, getNameResource02 result: " + autowiredName);
    }
}
