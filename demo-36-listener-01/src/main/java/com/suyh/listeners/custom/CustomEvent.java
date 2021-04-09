package com.suyh.listeners.custom;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件类型
 * ApplicationEvent 事件是同步执行的。
 */
public class CustomEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     *               发送事件的时候，会带一个实例对象。
     */
    public CustomEvent(String source) {
        super(source);
    }
}
