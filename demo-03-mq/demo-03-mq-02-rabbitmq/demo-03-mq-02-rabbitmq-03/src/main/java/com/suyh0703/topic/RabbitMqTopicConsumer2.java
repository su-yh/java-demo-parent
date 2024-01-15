package com.suyh0703.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author suyh
 * @since 2023-12-21
 */
public class RabbitMqTopicConsumer2 {
    private final static String EXCHANGE_NAME = "flink_output_topic_exchange";
    private final static String RETENTION_KEY = "paid_retention_key";
    private final static String QUEUE_NAME = "suyhQueueName_1";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.8.34");
        factory.setUsername("admin");
        factory.setPassword("aiteer");
        factory.setVirtualHost("/flinkhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, RETENTION_KEY);

        System.out.println("等待接收消息...");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String correlationId = delivery.getProperties().getCorrelationId();
            String message = new String(delivery.getBody());
            System.out.println("接收的消息(" + EXCHANGE_NAME + "), routing key: " + RETENTION_KEY + ": " + message + ", correlationId: " + correlationId);
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息消费被中断");
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
