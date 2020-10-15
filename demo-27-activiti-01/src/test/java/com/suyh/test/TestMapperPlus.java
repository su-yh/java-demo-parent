package com.suyh.test;

import com.suyh.Application2701;
import com.suyh.entity.FormPropertyTemplateEntity;
import com.suyh.form.PropertyType;
import com.suyh.mapper.FormPropertyTemplateMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
        FormPropertyTemplateEntity formPropertyTemplateEntity = new FormPropertyTemplateEntity();

        formPropertyTemplateEntity.setId(100L);
        formPropertyTemplateEntity.setBusinessKey("businessKey");
        formPropertyTemplateEntity.setPropertyType(PropertyType.LONG.name());
        formPropertyTemplateEntity.setRequired("Y");
        formPropertyTemplateEntity.setVarKey("varKey");
        formPropertyTemplateEntity.setDescription("nothing");
        formPropertyTemplateEntity.setPattern("yyyy-MM-dd HH:mm:ss.SSS");
        formPropertyTemplateEntity.setValueCustom("[]");

        mapper.insert(formPropertyTemplateEntity);

        FormPropertyTemplateEntity formPropertyTemplateEntity2 = mapper.selectById(formPropertyTemplateEntity.getId());
        log.info("form: {}", ToStringBuilder.reflectionToString(
                formPropertyTemplateEntity2, ToStringStyle.JSON_STYLE));
    }
}
