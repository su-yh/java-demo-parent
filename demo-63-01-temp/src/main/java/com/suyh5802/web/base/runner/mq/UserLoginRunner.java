package com.suyh5802.web.base.runner.mq;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.suyh5802.web.base.config.properties.RmqProperties;
import com.suyh5802.web.base.entity.UserLoginEntity;
import com.suyh5802.web.base.mapper.UserLoginMapper;
import com.suyh5802.web.base.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 用户登录
 * 就是将表tb_user_login 中的数据写到mq 队长中
 *
 * @author suyh
 * @since 2023-12-27
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserLoginRunner implements ApplicationRunner {
    // 对应表 tb_user_login
    private final static String POLY_TB_USER_LOGIN = "poly_tb_user_login_pre";

    private final UserLoginMapper userLoginMapper;
    private final RmqProperties rmqProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 是否启用下面的代码功能
        boolean enabled = false;
        if (!enabled) {
            return;
        }

        LambdaQueryWrapper<UserLoginEntity> queryWrapper =  new LambdaQueryWrapper<>();
        queryWrapper.ge(UserLoginEntity::getId, 20002);
        queryWrapper.le(UserLoginEntity::getId, 20105);
        List<UserLoginEntity> entities = userLoginMapper.selectList(queryWrapper);
        if (entities == null || entities.isEmpty()) {
            log.info("UserLoginEntity list is empty, tb_user.");
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
            channel.queueDeclare(POLY_TB_USER_LOGIN, true, false, false, null);

            for (UserLoginEntity entity : entities) {
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
                channel.basicPublish("", POLY_TB_USER_LOGIN, properties, message.getBytes(StandardCharsets.UTF_8));
                System.out.println("消息发送完毕");
            }

        }
    }
}
