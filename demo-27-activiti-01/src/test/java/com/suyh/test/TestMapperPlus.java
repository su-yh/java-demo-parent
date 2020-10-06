package com.suyh.test;

import com.suyh.Application2701;
import com.suyh.form.entity.FormPropertyTemplateEntity;
import com.suyh.mapper.FormPropertyTemplateMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application2701.class)
@Slf4j
public class TestMapperPlus {
    @Resource
    private FormPropertyTemplateMapper mapper;

    @Test
    public void test01() {
        FormPropertyTemplateEntity formPropertyTemplateEntity = mapper.selectById(100);
        log.info("form: {}", formPropertyTemplateEntity);
    }
}
