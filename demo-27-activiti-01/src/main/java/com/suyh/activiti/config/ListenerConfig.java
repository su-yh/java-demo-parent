package com.suyh.activiti.config;

import com.suyh.activiti.listener.GlobalListener;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 对事件监听的配置
 */
@Component
@Slf4j
public class ListenerConfig implements ProcessEngineConfigurationConfigurer {
    @Resource
    private GlobalListener globalListener;

    @Override
    public void configure(SpringProcessEngineConfiguration engineConfiguration) {
        List<ActivitiEventListener> activitiEventListener = new ArrayList<>();
        // 配置全局监听器
        activitiEventListener.add(globalListener);
        engineConfiguration.setEventListeners(activitiEventListener);
    }
}
