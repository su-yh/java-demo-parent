package com.suyh5504.websocket.server.interceptor;

import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

public class SocketChannelInterceptor implements ChannelInterceptor {

    /**
     * 在完成发送之后进行调用，不管是否有异常发生，一般用于资源清理
     */
    @Override
    public void afterSendCompletion(
            @NonNull Message<?> message, @NonNull MessageChannel channel, boolean sent, Exception ex) {
        System.out.println("SocketChannelIntecepter->afterSendCompletion");
    }


    /**
     * 在消息被实际发送到频道之前调用
     */
    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        System.out.println("SocketChannelIntecepter->preSend");
        return message;
    }

    /**
     * 发送消息调用后立即调用
     */
    @Override
    public void postSend(@NonNull Message<?> message, @NonNull MessageChannel channel, boolean sent) {
        System.out.println("SocketChannelIntecepter->postSend");

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);//消息头访问器

        if (headerAccessor.getCommand() == null) {
            // 避免非stomp消息类型，例如心跳检测
            return;
        }

        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        System.out.println("SocketChannelIntecepter -> sessionId = " + sessionId);

        switch (headerAccessor.getCommand()) {
            case CONNECT:
                connect(sessionId);
                break;
            case DISCONNECT:
                disconnect(sessionId);
                break;
            case SUBSCRIBE:
                break;
            case UNSUBSCRIBE:
                break;
            default:
                break;
        }
    }

    //连接成功
    private void connect(String sessionId) {
        System.out.println("connect sessionId=" + sessionId);
    }

    //断开连接
    private void disconnect(String sessionId) {
        System.out.println("disconnect sessionId=" + sessionId);
        //用户下线操作
    }
}
