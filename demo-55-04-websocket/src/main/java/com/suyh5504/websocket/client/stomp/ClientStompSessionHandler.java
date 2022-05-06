package com.suyh5504.websocket.client.stomp;

import com.suyh5504.websocket.vo.Message;
import com.suyh5504.websocket.vo.OutputMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
@Slf4j
public class ClientStompSessionHandler extends StompSessionHandlerAdapter {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        log.info("getPayloadType");
        return OutputMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("headers: {}, payload: {}", headers, payload);
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("afterConnected");
        session.subscribe("/topic/ticks", this);
        session.subscribe("/topic/messages", this);

        Message msg = new Message();
        msg.setFrom("suyh");
        msg.setText("suyh-text");
        session.send("/app/chat", msg);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("handleException", exception);
        super.handleException(session, command, headers, payload, exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.info("handleTransportError");
        super.handleTransportError(session, exception);
    }
}
