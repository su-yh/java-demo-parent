package com.suyh.activiti.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GlobalListener implements ActivitiEventListener {

    /**
     * 事件监听处理
     * @param event 当前事件
     */
    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType eventType = event.getType();
        if (ActivitiEventType.PROCESS_STARTED.equals(eventType)) {
            log.info("eventListner: 流程启动 {} 流程实例ID: {}", eventType, event.getProcessInstanceId());
        } else if (ActivitiEventType.PROCESS_COMPLETED.equals(eventType)) {
            log.info("eventListner: 流程结束 {} 流程实例ID: {}", eventType, event.getProcessInstanceId());
        } else {
            log.error("eventListener: 其他事件 {}", eventType);
        }
    }

    @Override
    public boolean isFailOnException() {
        log.error("isFailOnException");
        return false;
    }
}
