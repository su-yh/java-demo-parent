package com.suyh.test;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFormService {
    @Resource
    private FormService formService;
    @Resource
    private RepositoryService repositoryService;

    @Test
    public void test01() {
        final String processDefinitionKey = "my-process";
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                // 取版本最高的一个
                .orderByProcessDefinitionVersion().desc()
                .listPage(0, 1);
        if (list == null || list.isEmpty()) {
            log.warn("list is empty");
            return;
        }
        ProcessDefinition processDefinition = list.get(0);
        log.info("processDefinition: {}", ToStringBuilder.reflectionToString(processDefinition, ToStringStyle.JSON_STYLE));
        StartFormData startFormData = formService.getStartFormData(processDefinition.getId());

        log.info("startFormData: {}", ToStringBuilder.reflectionToString(startFormData, ToStringStyle.JSON_STYLE));

        List<FormProperty> formProperties = startFormData.getFormProperties();
        for (FormProperty formProperty : formProperties) {
            log.info("formProperty: {}", ToStringBuilder.reflectionToString(formProperty, ToStringStyle.JSON_STYLE));
        }
    }
}
