package com.suyh5504.websocket.server.controller;

import com.suyh5504.websocket.vo.Message;
import com.suyh5504.websocket.vo.OutputMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Slf4j
public class HandlerController {
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message, StompHeaderAccessor headerAccessor) throws Exception {
        log.info("message: {}", message);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }

    @Scheduled(fixedRate = 3000)
    public void sendTicks() {
        OutputMessage msg = new OutputMessage();
        msg.setFrom("server");
        msg.setText("server-text");
        msg.setTime("time");
        simpMessagingTemplate.convertAndSend("/topic/ticks", msg);
    }
}
