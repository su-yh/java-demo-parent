package com.suyh.listeners.single;

import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.RequestHandledEvent;

/**
 * 这是一个 web-specific 事件，告诉所有 bean HTTP 请求已经被服务。只能应用于使用DispatcherServlet的Web应用。
 * 在使用Spring作为前端的MVC控制器时，当Spring处理用户请求结束后，系统会自动触发该事件。
 */
public class RequestHandledEventListener implements ApplicationListener<RequestHandledEvent> {
    @Override
    public void onApplicationEvent(RequestHandledEvent event) {
        System.out.println("single RequestHandledEvent");
    }
}
