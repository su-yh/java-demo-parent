package com.suyh0703;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.suyh0703.util.RabbitMqUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author suyh
 * @since 2023-12-22
 */
public class ReceiveLogs01 {
    private static final String EXCHANGE_NAME = "flink_output_topic_exchange";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);

        String queueName = "Q1";
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, EXCHANGE_NAME, "cohort_key_1");

        System.out.println("等待接收消息....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("接收队列：" + queueName + "绑定键: " + delivery.getEnvelope().getRoutingKey() + "，消息：" + message);
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
