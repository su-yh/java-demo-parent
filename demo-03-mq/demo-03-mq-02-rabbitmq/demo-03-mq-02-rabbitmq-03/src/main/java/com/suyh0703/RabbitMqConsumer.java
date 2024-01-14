package com.suyh0703;

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
public class RabbitMqConsumer {
    private final static String QUEUE_NAME = "hello";
    public final static String POLY_TB_USER = "poly_tb_user_pre";
    public final static String POLY_TB_USER_LOGIN = "poly_tb_user_login_pre";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("139.9.50.208");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setVirtualHost("/flinkhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println("等待接收消息...");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String correlationId = delivery.getProperties().getCorrelationId();
            String message = new String(delivery.getBody());
            System.out.println("接收的消息(" + POLY_TB_USER_LOGIN + "): " + message + ", correlationId: " + correlationId);
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息消费被中断");
        };

        channel.basicConsume(POLY_TB_USER_LOGIN, true, deliverCallback, cancelCallback);
    }
}
