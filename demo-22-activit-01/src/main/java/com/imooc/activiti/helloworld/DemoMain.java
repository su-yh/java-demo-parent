package com.imooc.activiti.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

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
        // 启动运行流程
        // 处理流程任务

        log.info("结束我们的程序");
    }
}
