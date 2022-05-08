package com.suyh5504.websocket.server.config;

import com.suyh5504.websocket.server.interceptor.HttpHandShakeInterceptor;
import com.suyh5504.websocket.server.interceptor.SocketChannelInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        HttpHandShakeInterceptor httpHandShakeInterceptor = new HttpHandShakeInterceptor();
        // 原来这里加上withSockJS() 的话，客户端需要添加一个/websocket 后缀才能连接上呢。
        registry.addEndpoint("/stock-ticks").addInterceptors(httpHandShakeInterceptor);
        registry.addEndpoint("/stock-ticks").addInterceptors(httpHandShakeInterceptor)
                .setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        // 这个是指客户端访问服务端的时候需要添加的前缀
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new SocketChannelInterceptor());
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(new SocketChannelInterceptor());
    }
}
