package com.suyh0703;

import com.rabbitmq.client.AMQP;
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
public class RabbitMqProducer {
    public final static String POLY_TB_USER = "poly_tb_user_pre";
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.8.34");
        factory.setUsername("admin");
        factory.setPassword("aiteer");
        factory.setVirtualHost("/flinkhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            /*
             * 生成一个队列
             * 1. 队列名称
             * 2. 队列里面的消息是否持久化 默认消息存储在内存中
             * 3. 该队列是否只供一个消费都进行消费 是否进行共享 true 可以多个消费者消费
             * 4. 是否自动删除 最后一个消费者端连接以后 该队列 是否自动删除  true 自动删除
             * 5. 其他参数
             */
            channel.queueDeclare(POLY_TB_USER, true, false, false, null);
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
            channel.basicPublish("", POLY_TB_USER, properties, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("消息发送完毕");
        }

    }
}
