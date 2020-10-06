package com.suyh.test;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

@Slf4j
public class TestFormService {
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_druid.cfg.xml");
    @Test
    @Deployment(resources = "processes/second_approve.bpmn")
    public void test01() {
        final String processDefinitionKey = "second_approve";
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey).list();
        log.info("size: {}", list.size());
        if (true) {
            return;
        }
        FormService formService = activitiRule.getFormService();
        StartFormData startFormData = formService.getStartFormData("LoanRequestProcess:3:12505");


        log.info("startFormData: {}", ToStringBuilder.reflectionToString(startFormData, ToStringStyle.JSON_STYLE));
    }
}
