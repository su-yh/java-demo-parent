package com.suyh.listeners.custom;

import org.springframework.context.ApplicationListener;

/**
 * 自定义事件的监听器实现。
 * 当事件被发布时的具体处理过程。
 * 使它生效有两种方式：
 * 1. 配置成一个bean 对象
 * 2. 在spring.factories 中配置为一个事件监听器 ApplicationListener
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("single CustomEvent, event source: " + event.getSource());
    }
}
