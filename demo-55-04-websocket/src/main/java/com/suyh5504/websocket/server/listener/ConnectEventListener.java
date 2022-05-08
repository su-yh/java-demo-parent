package com.suyh5504.websocket.server.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Component
public class ConnectEventListener implements ApplicationListener<SessionConnectEvent> {

    public void onApplicationEvent(SessionConnectEvent event) {
        // MessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(event.getMessage());

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        StompCommand stompCommand = headerAccessor.getCommand();
        if (stompCommand == null) {
            System.out.println("非stomp 协议websocket 连接");
            return;
        }
        System.out.println("【ConnectEventListener监听器事件 类型】" + stompCommand.getMessageType());
    }

}
