package com.suyh.listeners.multi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;

/**
 * 多事件监听器
 * 要使其生效有两个方法：
 * 1. 将其配置在spring.factories 文件的 ApplicationListener 配置项中
 * 2. 配置成一个bean 对象
 */
@Slf4j
public class MultiEventListener implements GenericApplicationListener, ApplicationListener<ApplicationEvent> {

    /**
     * 根据ApplicationEvent 事件类型进行过滤，只有返回true 时才会执行事件监听
     */
    @Override
    public boolean supportsEventType(ResolvableType eventType) {
        System.out.println("resolvableType: " + eventType.getType());
        return true;
    }

    /**
     * 根据事件发布时给的实例类型进行匹配
     */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        System.out.println("sourceType: " + sourceType);
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("multi AppListener execute...");
    }
}
