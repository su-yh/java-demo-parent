package com.suyh.test;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFormService {
    @Resource
    private FormService formService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private TaskService taskService;
    // 流程定义KEY
    private final String processDefinitionKey = "my-process";

    /**
     * 查询表单数据
     */
    @Test
    public void testQueryProcessFormData() {
        // 通过流程定义KEY，以及最高版本，找到对应的流程定义属性。从而获取到流程定义ID.
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

        Task task = taskService.createTaskQuery().taskId("taskId").singleResult();
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
    }

    /**
     * 通过表单数据启动流程实例
     */
    @Test
    public void testFormSubmitProcessInstance() {
        //根据form启动流程
        Map<String, String> properties = new HashMap<>();
        properties.put("message", "my test message");
//        ProcessInstance processInstance = formService.submitStartFormData(processDefinition.getId(), properties);

    }
}
