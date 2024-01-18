package com.suyh0703.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author suyh
 * @since 2023-12-21
 */
public class RabbitMqTopicProducer {
    private final static String EXCHANGE_NAME = "flink_output_topic_exchange";
    private final static String COHORT_KEY = "cohort_key";
    private final static String RETENTION_KEY = "paid_retention_key";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.8.34");
        factory.setUsername("admin");
        factory.setPassword("aiteer");
        factory.setVirtualHost("/flinkhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true, false, null);
            String message = "hello world";

            /*
             * 发送一个消息
             * 1. 发送到哪个交换机
             * 2. 路由的key 是哪个
             * 3. 其他的参数信息
             * 4. 发送消息的消息体
             */
            AMQP.BasicProperties properties = new AMQP.BasicProperties();
            properties = properties.builder().correlationId(UUID.randomUUID().toString()).build();
            channel.basicPublish(EXCHANGE_NAME, COHORT_KEY, properties, message.getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_NAME, RETENTION_KEY, properties, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("消息发送完毕");
        }

    }
}
