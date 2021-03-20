package com.suyh.listeners.single;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 该事件在springboot启动失败是调用
 */
public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        System.out.println("single ApplicationFailedEvent");
    }
}
