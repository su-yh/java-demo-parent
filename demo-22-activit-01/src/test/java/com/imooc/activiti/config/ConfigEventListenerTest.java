package com.imooc.activiti.config;

import com.imooc.activiti.helloworld.event.CustomerEventListener;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

@Slf4j
public class ConfigEventListenerTest {
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-event-listener.cfg.xml");

    /**
     * eventListeners: 监听所有事件派发通知
     * typedEventListeners: 监听指定事件类型的通知
     * activiti:eventListener: 只监听特定流程定义事件
     * API
     * ActivitiEvent: 事件对象
     * ActivitiEventListener: 监听器
     * ActivitiEventType: 事件类型  枚举
     */
    @Test
    @Deployment(resources = {"my-process-event-listener.bpmn20.xml"})
    public void test() {
        ProcessInstance processInstance = activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        activitiRule.getTaskService().complete(task.getId());

        // 通过API 的方式添加自定义监听
        activitiRule.getRuntimeService().addEventListener(new CustomerEventListener("API"));
        // 发送一个自定义事件
        activitiRule.getRuntimeService().dispatchEvent(new ActivitiEventImpl(ActivitiEventType.CUSTOM));
    }
}
