package com.suyh5802.web.base.runner.mq;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.suyh5802.web.base.entity.AdjustUserEntity;
import com.suyh5802.web.base.entity.UserLoginEntity;
import com.suyh5802.web.base.enums.PN;
import com.suyh5802.web.base.mapper.AdjustUserMapper;
import com.suyh5802.web.base.runner.mq.util.MqCorrelationIdUtils;
import com.suyh5802.web.base.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
public class RandomPlusUserLoginRunner implements ApplicationRunner {
    // 对应表 tb_user_login
    private final static String POLY_TB_USER_LOGIN = "poly_tb_user_login_pre";

    private final AdjustUserMapper adjustUserMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 是否启用下面的代码功能
        boolean enabled = false;
        if (!enabled) {
            return;
        }

        Page<AdjustUserEntity> page = Page.of(1, 100); // AdjustUserEntity 的分页
        List<UserLoginEntity> entities = makeEntityList(page);

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
            channel.queueDeclare(POLY_TB_USER_LOGIN, true, false, false, null);

            for (UserLoginEntity entity : entities) {
                long correlationId = MqCorrelationIdUtils.getCorrelationId();
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
            }

            System.out.println("消息发送完毕, UserLoginEntity size: " + entities.size());
        }
    }

    private List<UserLoginEntity> makeEntityList(Page<AdjustUserEntity> page) {
        AdjustUserEntity sourceAdEntity = adjustUserMapper.selectById(161529474L);   // 源方提供的测试数据
        List<AdjustUserEntity> adUserEntities = queryAdUserEntities(page);// 查询大于这个ID 的其他数据
        log.info("query ad user entities, size: {}", adUserEntities.size());

        List<UserLoginEntity> entities = new ArrayList<>();

        // 这些是根据那边提供的测试数据，来生成的有用的测试数据。
        String originChannelId = sourceAdEntity.getChannelid();

        long currentTimeMillis = System.currentTimeMillis();
        int src = 2;    // 1：用户注册；2：用户登录

        for (AdjustUserEntity adUserEntity : adUserEntities) {
            for (int i = 0; i < 10; i++) {
                currentTimeMillis++;

                String channelId = adUserEntity.getChannelid();
                String gaid = adUserEntity.getGaid();

                for (PN pn : PN.values()) {
                    Long uidNumber = DefaultIdentifierGenerator.getInstance().nextId(null);
                    Long vungoUserLoginId = DefaultIdentifierGenerator.getInstance().nextId(null);

                    UserLoginEntity entity = new UserLoginEntity();
                    entity.setUid(uidNumber + "").setSrc(src).setChannel(channelId).setCtime(currentTimeMillis).setGaid(gaid)
                            .setOriginChannel(originChannelId).setVungoUserLoginId(vungoUserLoginId).setPn(pn.name());

                    entities.add(entity);
                }
            }
        }

        return entities;
    }

    private List<AdjustUserEntity> queryAdUserEntities(Page<AdjustUserEntity> page) {
        LambdaQueryWrapper<AdjustUserEntity> queryWrapper = new LambdaQueryWrapper<>();

        Page<AdjustUserEntity> entityPage = adjustUserMapper.selectPage(page, queryWrapper);
        return entityPage.getRecords();
    }
}
