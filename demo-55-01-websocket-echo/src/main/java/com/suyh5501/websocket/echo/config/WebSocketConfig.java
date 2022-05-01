package com.suyh5501.websocket.echo.config;

import com.suyh5501.websocket.echo.handle.EchoTextWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //  为指定的URL 配置具体的 WebSocketHandler
        WebSocketHandlerRegistration registration =  registry.addHandler(echoHandler(), "/echo");

        // registration 能够对 WebSocketHandler 进行配置
    }

    @Bean
    public WebSocketHandler echoHandler() {
        return new EchoTextWebSocketHandler();
    }
}

