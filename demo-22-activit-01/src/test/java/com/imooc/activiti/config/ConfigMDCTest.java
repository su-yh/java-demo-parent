package com.imooc.activiti.config;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

@Slf4j
public class ConfigMDCTest {
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mdc.cfg.xml");

    @Test
    @Deployment(resources = {"my-process-mdc.bpmn20.xml"})
    public void test() {
        LogMDC.setMDCEnabled(true);

        ProcessInstance processInstance = activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process");
        Assert.assertNotNull(processInstance);

        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        Assert.assertEquals("Activiti is awesome!", task.getName());
        activitiRule.getTaskService().complete(task.getId());
    }
}
