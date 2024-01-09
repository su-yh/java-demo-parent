package com.suyh5802.web.base.runner.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.suyh5802.web.base.config.properties.RmqProperties;
import com.suyh5802.web.base.entity.WithdrawalEntity;
import com.suyh5802.web.base.mapper.WithdrawalMapper;
import com.suyh5802.web.base.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author suyh
 * @since 2023-12-28
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class WithdrawalRunner implements ApplicationRunner {
    private final static String POLY_TB_WITHDRAWAL = "poly_tb_withdrawal_pre";

    private final WithdrawalMapper withdrawalMapper;
    private final RmqProperties rmqProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 是否启用下面的代码功能
        boolean enabled = false;
        if (!enabled) {
            return;
        }

        List<WithdrawalEntity> entities = withdrawalMapper.selectList(null);
        if (entities == null || entities.isEmpty()) {
            log.info("WithdrawalEntity list is empty, tb_user.");
            return;
        }

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rmqProperties.getHost());
        factory.setPort(rmqProperties.getPort());
        factory.setUsername(rmqProperties.getUsername());
        factory.setPassword(rmqProperties.getPassword());
        factory.setVirtualHost(rmqProperties.getVirtualHost());

        long currentTimeMillis = System.currentTimeMillis();

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            /*
             * 生成一个队列
             * 1. 队列名称
             * 2. 队列里面的消息是否持久化 默认消息存储在内存中
             * 3. 该队列是否只供一个消费都进行消费 是否进行共享 true 可以多个消费者消费
             * 4. 是否自动删除 最后一个消费者端连接以后 该队列 是否自动删除  true 自动删除
             * 5. 其他参数
             */
            channel.queueDeclare(POLY_TB_WITHDRAWAL, true, false, false, null);

            for (WithdrawalEntity entity : entities) {
                long correlationId = ++currentTimeMillis;
                entity.setCorrelationId(correlationId);
                String message = JsonUtils.serializable(entity);

                /*
                 * 发送一个消息
                 * 1. 发送到哪个交换机
                 * 2. 路由的key 是哪个
                 * 3. 其他的参数信息
                 * 4. 发送消息的消息体
                 */
                AMQP.BasicProperties properties = new AMQP.BasicProperties();
                properties = properties.builder().correlationId(correlationId + "").build();
                channel.basicPublish("", POLY_TB_WITHDRAWAL, properties, message.getBytes(StandardCharsets.UTF_8));
                System.out.println("消息发送完毕");
            }
        }
    }
}
