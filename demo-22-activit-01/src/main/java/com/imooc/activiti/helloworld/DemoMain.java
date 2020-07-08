package com.imooc.activiti.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;

@Slf4j
public class DemoMain {
    public static void main(String[] args) {
        log.info("启动我们的程序");
        // 创建流程引擎
        // 使用内存数据库创建流程引擎
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String name = processEngine.getName();
        String version = ProcessEngine.VERSION;
        log.info("流程引擎名称 {}, 版本: {}", name, version);

        // 部署流程定义文件
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
//        deploymentBuilder.addClasspathResource("second_approve_02_form.bpmn20.xml");
        deploymentBuilder.addClasspathResource("second_approve.bpmn20.xml");
        Deployment deploy = deploymentBuilder.deploy();
        String deployId = deploy.getId();
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        log.info("流程定义名称 {}, 流程ID {}", processDefinition.getName(), processDefinition.getId());
        // 启动运行流程
        // 处理流程任务

        log.info("结束我们的程序");
    }
}
