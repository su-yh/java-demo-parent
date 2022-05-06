package com.suyh5504.websocket.client.init;

import com.suyh5504.websocket.client.stomp.ClientStompSessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.Resource;

@Component
@Slf4j
public class ApplicationStarter implements ApplicationRunner {
    @Resource
    private ClientStompSessionHandler clientStompSessionHandler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        String url = "ws://localhost:5504/stock-ticks/websocket";
        stompClient.connect(url, clientStompSessionHandler);
    }
}
