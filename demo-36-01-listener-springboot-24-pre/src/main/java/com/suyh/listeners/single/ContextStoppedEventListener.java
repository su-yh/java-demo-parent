package com.suyh.listeners.single;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * 当使用 ConfigurableApplicationContext 接口中的 stop() 停止 ApplicationContext 时，发布这个事件。
 * 你可以在接受到这个事件后做必要的清理的工作。
 *
 * ContextClosedEvent表示应用程序正在被完全关闭，而ContextStoppedEvent表示应用程序上下文的活动被暂停。
 */
public class ContextStoppedEventListener implements ApplicationListener<ContextStoppedEvent> {
    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        System.out.println("single ContextStoppedEvent");
    }
}
