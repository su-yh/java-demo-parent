package com.imooc.activiti.helloworld.event;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;

@Slf4j
public class ProcessTypedEventListener implements ActivitiEventListener {
    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType eventType = event.getType();
        if (ActivitiEventType.PROCESS_STARTED.equals(eventType)) {
            log.info("typedEventListener: 流程启动 {}     {}", eventType, event.getProcessInstanceId());
        } else if (ActivitiEventType.PROCESS_COMPLETED.equals(eventType)) {
            log.info("typedEventListener: 流程结束 {}     {}", eventType, event.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
