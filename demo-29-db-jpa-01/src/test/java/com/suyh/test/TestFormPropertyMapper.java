package com.suyh.test;

import com.suyh.entity.FormPropertyTemplateEntity;
import com.suyh.mapper.FormProperTemplateMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestFormPropertyMapper {
    @Resource
    private FormProperTemplateMapper formMapper;

    @Test
    public void testInsert() {
        FormPropertyTemplateEntity entity = new FormPropertyTemplateEntity();
        entity.setBusinessKey("testBusinessKey");
        entity.setPropertyType("testProperty");
        entity.setRequired("Y");
        entity.setVarKey("testVarKey");
        entity.setDescription("testDescription");
        entity.setPattern("yyyy-MM-dd HH:mm:ss.SSS");
        entity.setValueCustom("testValueCustom");
        entity.setValueCustomText("testValueCustomeText");
        formMapper.save(entity);
        log.info("save entity: {}", ToStringBuilder.reflectionToString(entity, ToStringStyle.JSON_STYLE));
    }

    @Test
    public void testQuery() {
        List<FormPropertyTemplateEntity> formList = formMapper.findAll();
        log.info("formList: {}", formList);
    }

    // 分页查询
    @Test
    public void testQueryPage() {
        int curPage = 0;    // 分页是从0 开始计数的
        int pageSize = 10;
        Sort sortBy = Sort.by(Sort.Direction.DESC, "updateTime");//这里给的属性，是需要Entity 中定义的成员变量名
        Pageable pageable = PageRequest.of(curPage, pageSize, sortBy);

        // 创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withMatcher("materialName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("registerTime", ExampleMatcher.GenericPropertyMatchers.contains()) //姓名采用“开始匹配”的方式查询
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains()) //姓名采用“开始匹配”的方式查询
                .withIgnorePaths("id");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        FormPropertyTemplateEntity queryEntity = new FormPropertyTemplateEntity();
        queryEntity.setBusinessKey("testBusinessKey");
        Example<FormPropertyTemplateEntity> queryExample = Example.of(queryEntity);
        Page<FormPropertyTemplateEntity> pageResult = formMapper.findAll(queryExample, pageable);
        long total = pageResult.getTotalElements();
        log.info("总数: {}", total);

        for (FormPropertyTemplateEntity entity : pageResult) {
            log.info("实体数据01: {}", ToStringBuilder.reflectionToString(entity, ToStringStyle.JSON_STYLE));
        }

        Iterator<FormPropertyTemplateEntity> iter = pageResult.iterator();
        while (iter.hasNext()) {
            FormPropertyTemplateEntity entity = iter.next();
            log.info("实体数据02: {}", ToStringBuilder.reflectionToString(entity, ToStringStyle.JSON_STYLE));
        }

        List<FormPropertyTemplateEntity> entityList = pageResult.getContent();
        for (FormPropertyTemplateEntity entity : entityList) {
            log.info("实体数据: {}", ToStringBuilder.reflectionToString(entity, ToStringStyle.JSON_STYLE));
        }
    }
}
