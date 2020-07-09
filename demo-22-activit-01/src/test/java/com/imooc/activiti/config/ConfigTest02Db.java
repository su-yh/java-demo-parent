package com.imooc.activiti.config;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

@Slf4j
public class ConfigTest02Db {

    @Test
    public void testConfig01() {
        // 通过默认配置文件(activiti.cfg.xml)进行创建，并依赖spring 进行处理bean 对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResourceDefault();

        log.info("configuration: {}", configuration);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        log.info("获取流程引擎: {}", processEngine.getName());
        processEngine.close();
    }

    @Test
    public void testConfig02() {
        // 通过指定配置文件进行创建，并依赖spring 进行处理bean 对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti-druid.cfg.xml");

        log.info("configuration: {}", configuration);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        log.info("获取流程引擎: {}", processEngine.getName());
        processEngine.close();
    }
}
