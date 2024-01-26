package com.suyh07011.init;

import com.suyh07011.config.TopicRabbitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author suyh
 * @since 2023-11-29
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TestApplicationRunner implements ApplicationRunner {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendTopicMessage1();
        sendTopicMessage2();
    }

    public void sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend(TopicRabbitConfig.EXCHANGE, TopicRabbitConfig.TOPIC_MAN, manMap);
    }

    public void sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        Map<String, Object> womenMap = new HashMap<>();
        womenMap.put("messageId", messageId);
        womenMap.put("messageData", messageData);
        womenMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend(TopicRabbitConfig.EXCHANGE, TopicRabbitConfig.TOPIC_WOMEN, womenMap, message -> {
            // 发送消息的后置处理回调，在发送到rmq 之前调用， 在这里可以添加相关属性
            MessageProperties properties = message.getMessageProperties();
            if (properties == null) {
                properties = new MessageProperties();
            }
            // 在这里可以添加属性
            // properties.setCorrelationId(UUID.randomUUID().toString());
            return message;
        });
    }
}
