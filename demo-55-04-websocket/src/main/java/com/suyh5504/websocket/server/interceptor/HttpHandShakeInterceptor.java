package com.suyh5504.websocket.server.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class HttpHandShakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(
            @NonNull ServerHttpRequest request, @NonNull  ServerHttpResponse response,
            @NonNull  WebSocketHandler wsHandler, @NonNull  Map<String, Object> attributes) throws Exception {

        System.out.println("【握手拦截器】beforeHandshake");

        if(request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
            HttpSession session =  servletRequest.getServletRequest().getSession();
            String sessionId = session.getId();
            System.out.println("【握手拦截器】beforeHandshake sessionId="+sessionId);
            attributes.put("sessionId", sessionId);
        }

        return true;
    }

    @Override
    public void afterHandshake(
            @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response,
            @NonNull WebSocketHandler wsHandler, Exception exception) {
        System.out.println("【握手拦截器】afterHandshake");

        if(request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
            HttpSession session =  servletRequest.getServletRequest().getSession();
            String sessionId = session.getId();
            System.out.println("【握手拦截器】afterHandshake sessionId="+sessionId);
        }
    }
}
