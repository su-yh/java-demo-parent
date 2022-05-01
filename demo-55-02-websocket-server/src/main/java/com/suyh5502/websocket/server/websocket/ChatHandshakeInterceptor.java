package com.suyh5502.websocket.server.websocket;

import com.suyh5502.websocket.server.domain.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * websocket的链接建立是基于http握手协议，我们可以添加一个拦截器处理握手之前和握手之后过程
 *
 * @author BoBo
 */
@Component
public class ChatHandshakeInterceptor implements HandshakeInterceptor {

    /**
     * 握手之前，若返回false，则不建立链接
     */
    @Override
    public boolean beforeHandshake(
            @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response,
            @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) {
        System.out.println("--------------握手开始...");
        HttpHeaders headers = request.getHeaders();
        String id = headers.getFirst("id");
        String nickName = headers.getFirst("NickName");
        if (id == null || nickName == null) {
            System.out.println("id or nick name is null");
            return false;
        }
        User user = new User();
        user.setId(id);
        user.setNickname(nickName);
        attributes.put("loginUser", user);
        return true;
    }

    /**
     * 握手之后
     */
    @Override
    public void afterHandshake(
            @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response,
            @NonNull WebSocketHandler wsHandler, Exception exception) {
        System.out.println("--------------握手成功啦...");
    }
}
