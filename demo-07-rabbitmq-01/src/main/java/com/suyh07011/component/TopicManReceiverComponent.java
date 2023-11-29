package com.suyh07011.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author suyh
 * @since 2023-11-29
 */
@Component
@RequiredArgsConstructor
@RabbitListener(queues = "topic.man")
@Slf4j
public class TopicManReceiverComponent {
    @RabbitHandler
    public void process(Map<String, Object> testMessage) {
        System.out.println("TopicManReceiver 消费者收到消息：" + testMessage.toString());
    }
}
