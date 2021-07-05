package com.suyh.listeners.single;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * ApplicationContext 被初始化或刷新时，该事件被发布。
 * 这也可以在 ConfigurableApplicationContext接口中使用 refresh() 方法来发生。
 * 此处的初始化是指：所有的Bean被成功装载，后处理Bean被检测并激活，
 * 所有Singleton Bean 被预实例化，ApplicationContext容器已就绪可用
 * ==================================================================
 * 具体的发布事件触发位置在：org.springframework.context.support.AbstractApplicationContext#refresh() 中调用finishRefresh() 方法中
 * 该位置调用是在try 块中的最后一行，即整个application 已经完全OK了。
 * 在org.springframework.context.support.AbstractApplicationContext#finishRefresh() 中有这么一行: publishEvent(new ContextRefreshedEvent(this));
 * 实际发布一个事件
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("single ContextRefreshedEvent.");
    }
}
