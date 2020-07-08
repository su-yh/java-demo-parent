package com.imooc.activiti.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class DemoMain {
    public static void main(String[] args) throws ParseException {
        log.info("启动我们的程序");
        // 创建流程引擎
        ProcessEngine processEngine = getProcessEngine();
        // 部署流程定义文件
        ProcessDefinition processDefinition = getProcessDefinition(processEngine);
        // 启动运行流程
        ProcessInstance processInstance = getProcessInstance(processEngine, processDefinition);
        // 处理流程任务
        processTask(processEngine, processInstance);

        log.info("结束我们的程序");
    }

    private static void processTask(ProcessEngine processEngine, ProcessInstance processInstance) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while (processInstance != null && !processInstance.isEnded()) {
            TaskService taskService = processEngine.getTaskService();
            List<Task> list = taskService.createTaskQuery().list();
            log.info("待处理任务数量: [{}]", list.size());

            for (Task task : list) {

                log.info("待处理任务: [{}]", task.getName());
                Map<String, Object> variables = getVariables(processEngine, scanner, task);

                taskService.complete(task.getId(), variables);
                processInstance = processEngine.getRuntimeService()
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .singleResult();
            }
        }
        scanner.close();
    }

    private static Map<String, Object> getVariables(
            ProcessEngine processEngine, Scanner scanner, Task task) throws ParseException {
        FormService formService = processEngine.getFormService();
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        Map<String, Object> variables = new HashMap<>();
        for (FormProperty property : formProperties) {
            String line = null;
            if (property.getType() instanceof StringFormType) {
                log.info("请输入 {} ? ", property.getName());
                line = scanner.nextLine();
                variables.put(property.getId(), line);
            } else if (property.getType() instanceof DateFormType) {
                log.info("请输入 {} ? 格式(yyyy-MM-dd)", property.getName());
                line = scanner.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(line);
                variables.put(property.getId(), date);
            } else {
                log.info("类型暂不支持 {}", property.getType());
            }
            log.info("您输入的内容是 [{}]", line);
        }
        return variables;
    }

    private static ProcessInstance getProcessInstance(ProcessEngine processEngine, ProcessDefinition processDefinition) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(
                processDefinition.getId());
        log.info("启动流程, process definition key: [{}]", processInstance.getProcessDefinitionKey());

        return processInstance;
    }

    private static ProcessDefinition getProcessDefinition(ProcessEngine processEngine) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
//        deploymentBuilder.addClasspathResource("second_approve_02_form.bpmn20.xml");
        deploymentBuilder.addClasspathResource("second_approve.bpmn20.xml");
        Deployment deploy = deploymentBuilder.deploy();
        String deployId = deploy.getId();
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        log.info("流程定义名称 [{}], 流程ID [{}], 部署ID: [{}]",
                processDefinition.getName(),
                processDefinition.getId(),
                deployId);
        return processDefinition;
    }

    private static ProcessEngine getProcessEngine() {

        // 使用内存数据库创建流程引擎
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String name = processEngine.getName();
        String version = ProcessEngine.VERSION;
        log.info("流程引擎名称 [{}], 版本: [{}]", name, version);
        return processEngine;
    }
}
