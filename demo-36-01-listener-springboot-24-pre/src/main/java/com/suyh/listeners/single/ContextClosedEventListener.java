package com.suyh.listeners.single;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * 当使用 ConfigurableApplicationContext 接口中的 close() 方法关闭 ApplicationContext 时，该事件被发布。
 * 一个已关闭的上下文到达生命周期末端；它不能被刷新或重启。
 *
 * ContextClosedEvent表示应用程序正在被完全关闭，而ContextStoppedEvent表示应用程序上下文的活动被暂停。
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("single ContextClosedEvent");
    }
}
