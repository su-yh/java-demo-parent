package com.suyh.demo3602.listener;


import com.suyh.listeners.custom.CustomEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 以注解的形式进行实现事件监听器
 * 但是这个实现必须交给spring 进行管理所以需要添加 @Component 注释
 */
@Component
public class AnnotationListener {
    /**
     * 对两种事件监听生效(ContextRefreshedEvent、CustomEvent)
     *
     * @param event
     */
    @EventListener({ContextRefreshedEvent.class, CustomEvent.class})
    public void multiEvent(ApplicationEvent event) {
        if (event instanceof CustomEvent) {
            System.out.println("multiEvent CustomEvent doing");
        } else if (event instanceof ContextRefreshedEvent) {
            System.out.println("multiEvent ContextRefreshedEvent doing");
        }
    }

    /**
     * 对CustomEvent 事件监听生效
     */
    @EventListener
    public void listenCustomEvent(CustomEvent event) {
        System.out.println("custom event: " + event.getSource());
    }
}
