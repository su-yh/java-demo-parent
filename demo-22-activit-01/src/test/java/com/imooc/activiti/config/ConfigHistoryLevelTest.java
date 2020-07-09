package com.imooc.activiti.config;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ConfigHistoryLevelTest {
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-history.cfg.xml");

    @Test
    @Deployment(resources = {"my-process-history.bpmn20.xml"})
    public void test() {
        // 启动流程
        startProcessInstance();
        // 修改变量
        modifyVariable();
        // 提交表单 task
        submitTaskFormData();
        // 输出历史内容
        // - 输出历史活动
        showHistoryActivity();
        // - 历史变量
        showHistoryVariable();
        // - 输出历史用户任务
        showHistoryTask();
        // - 输出历史表单详情
        showHistoryForm();
        // - 输出历史详情
        showHistoryDetail();
    }

    private void showHistoryDetail() {
        List<HistoricDetail> historicDetails = activitiRule.getHistoryService()
                .createHistoricDetailQuery().listPage(0, 100);
        for (HistoricDetail historicDetail : historicDetails) {
            log.info("historicDetail: {}", toStr(historicDetail));
        }
        log.info("historicDetails.size: {}", historicDetails.size());
    }

    private void showHistoryForm() {
        List<HistoricDetail> historicDetailForms = activitiRule.getHistoryService()
                .createHistoricDetailQuery().formProperties().listPage(0, 100);
        for (HistoricDetail historicDetail : historicDetailForms) {
            log.info("historicDetail: {}", toStr(historicDetail));
        }
        log.info("historicDetailForms.size: {}", historicDetailForms.size());
    }

    private void showHistoryTask() {
        List<HistoricTaskInstance> historicTaskInstances
                = activitiRule.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .listPage(0, 100);
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            log.info("historicTaskInstance: {}", historicTaskInstance);
        }
        log.info("historicTaskInstances.size: {}", historicTaskInstances.size());
    }

    private void showHistoryVariable() {
        List<HistoricVariableInstance> historicVariableInstances
                = activitiRule.getHistoryService().createHistoricVariableInstanceQuery()
                .listPage(0, 100);
        for (HistoricVariableInstance historicVariableInstance : historicVariableInstances) {
            log.info("historicVariableInstance: {}", historicVariableInstance);
        }
        log.info("historicVariableInstances.size: {}", historicVariableInstances.size());
    }

    private void showHistoryActivity() {
        List<HistoricActivityInstance> historicActivityInstances
                = activitiRule.getHistoryService()
                .createHistoricActivityInstanceQuery()
                .listPage(0, 100);
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            log.info("historicActivityInstance: {}", historicActivityInstance);
        }
        log.info("historicActivityInstance.size: {}", historicActivityInstances.size());
    }

    private void submitTaskFormData() {
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        Map<String, String> properties = new HashMap<>();
        properties.put("formKey1", "valuef1");
        properties.put("formKey2", "valuef2");
        activitiRule.getFormService().submitTaskFormData(task.getId(), properties);
        // 这里提交完表单之就该task 就结束了，同时该流程也结束了。
        Assert.assertEquals("Activiti is awesome!", task.getName());
    }

    private void modifyVariable() {
        List<Execution> executions = activitiRule.getRuntimeService().createExecutionQuery()
                .listPage(0, 100);

        for (Execution execution : executions) {
            log.info("execution = {}", execution);
        }
        log.info("exection.size: {}", executions.size());
        String id = executions.iterator().next().getId();
        activitiRule.getRuntimeService().setVariable(id, "keyStart1", "value1_new");
    }

    private void startProcessInstance() {
        Map<String, Object> params = new HashMap<>();
        params.put("keyStart1", "value1");
        params.put("keyStart2", "value2");
        ProcessInstance processInstance = activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process", params);
        Assert.assertNotNull(processInstance);
    }

    public static String toStr(Object object) {
        return ToStringBuilder.reflectionToString(object, ToStringStyle.JSON_STYLE);
    }
}
