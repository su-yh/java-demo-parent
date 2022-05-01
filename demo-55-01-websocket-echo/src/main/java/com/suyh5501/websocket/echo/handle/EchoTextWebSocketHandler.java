package com.suyh5501.websocket.echo.handle;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.nio.charset.StandardCharsets;

/**
 * websocket 处理类：在请求信息的基础上加上“服务器返回：”，然后返回给客户端 * @author black *
 */
public class EchoTextWebSocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 收到的信息
        String requestMsg = message.getPayload();
        System.out.println("客户端请求：" + requestMsg);
        // 组织响应信息
        String responseMsg = "服务器返回: " + requestMsg;
        System.out.println(responseMsg);
        TextMessage respMsg = new TextMessage(responseMsg.getBytes(StandardCharsets.UTF_8));
        // 返回给客户端
        session.sendMessage(respMsg);
    }
}
