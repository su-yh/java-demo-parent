package com.imooc.activiti.config;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

@Slf4j
public class ConfigTest01 {

    @Test
    public void testConfig01() {
        // 通过默认配置文件进行创建，并依赖spring 进行处理bean 对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResourceDefault();

        log.info("configuration: {}", configuration);
    }

    @Test
    public void testConfig02() {
        // 不依赖配置文件就可以进行创建
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();

        log.info("configuration: {}", configuration);
    }
}
