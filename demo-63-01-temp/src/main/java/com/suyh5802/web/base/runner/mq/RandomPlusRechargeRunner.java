package com.suyh5802.web.base.runner.mq;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.suyh5802.web.base.config.properties.RmqProperties;
import com.suyh5802.web.base.entity.AdjustUserEntity;
import com.suyh5802.web.base.entity.RechargeEntity;
import com.suyh5802.web.base.enums.PN;
import com.suyh5802.web.base.mapper.AdjustUserMapper;
import com.suyh5802.web.base.runner.mq.util.MqCorrelationIdUtils;
import com.suyh5802.web.base.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author suyh
 * @since 2023-12-28
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RandomPlusRechargeRunner implements ApplicationRunner {
    // 对应表 tb_recharge
    private final static String POLY_TB_RECHARGE = "poly_tb_recharge_pre";

    private final AdjustUserMapper adjustUserMapper;
    private final RmqProperties rmqProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 是否启用下面的代码功能
        boolean enabled = false;
        if (!enabled) {
            return;
        }

        for (int i = 0; i < 10; i++) {
            Page<AdjustUserEntity> page = Page.of(1, 100); // AdjustUserEntity 的分页
            List<RechargeEntity> entities = makeEntityList(page);
            if (entities == null || entities.isEmpty()) {
                log.info("RechargeEntity list is empty, tb_user.");
                return;
            }
            log.info("make recharge entities, size: {}", entities.size());

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(rmqProperties.getHost());
            factory.setPort(rmqProperties.getPort());
            factory.setUsername(rmqProperties.getUsername());
            factory.setPassword(rmqProperties.getPassword());
            factory.setVirtualHost(rmqProperties.getVirtualHost());


            try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
                /*
                 * 生成一个队列
                 * 1. 队列名称
                 * 2. 队列里面的消息是否持久化 默认消息存储在内存中
                 * 3. 该队列是否只供一个消费都进行消费 是否进行共享 true 可以多个消费者消费
                 * 4. 是否自动删除 最后一个消费者端连接以后 该队列 是否自动删除  true 自动删除
                 * 5. 其他参数
                 */
                channel.queueDeclare(POLY_TB_RECHARGE, true, false, false, null);

                for (RechargeEntity entity : entities) {
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
                    properties = properties.builder().correlationId(correlationId + "")
                            // deliveryMode 的值必须为2 才会持久化到磁盘上，否则服务器重启数据就没了。
                            .deliveryMode(2).build();
                    channel.basicPublish("", POLY_TB_RECHARGE, properties, message.getBytes(StandardCharsets.UTF_8));
                }

                System.out.println("消息发送完毕, RechargeEntity size: " + entities.size());
            }
        }
    }

    private List<RechargeEntity> makeEntityList(Page<AdjustUserEntity> page) {
        AdjustUserEntity sourceAdEntity = adjustUserMapper.selectById(161529474L);   // 源方提供的测试数据
        List<AdjustUserEntity> adUserEntities = queryAdUserEntities(page);// 查询大于这个ID 的其他数据
        log.info("query recharge entities, size: {}", adUserEntities.size());

        List<RechargeEntity> entities  = new ArrayList<>();

        Random random = new Random();

        // 这些是根据那边提供的测试数据，来生成的有用的测试数据。
        String originChannelId = sourceAdEntity.getChannelid();

        long currentTimeMillis = System.currentTimeMillis();
        LocalDate localDate = LocalDate.now();
        String dateString = localDate.toString().replace("-", "");
        long dateLong = Long.parseLong(dateString);

        for (AdjustUserEntity adUserEntity : adUserEntities) {

            // adEntities 有1000 + 这里的循环次数要注意，10 就很多了。
            int size = 10;
            for (int i = 0; i < size; i++) {
                currentTimeMillis++;
                String channelId = adUserEntity.getChannelid();
                String gaid = adUserEntity.getGaid();

                for (PN pn : PN.values()) {
                    Long uid = DefaultIdentifierGenerator.getInstance().nextId(null);
                    Long vungoRechargeId = DefaultIdentifierGenerator.getInstance().nextId(null);

                    String uuidString = UUID.randomUUID().toString().replace("-", "");

                    RechargeEntity entity = new RechargeEntity();
                    entity.setUid(uid + "").setCtime(currentTimeMillis).setGoodsAmt(new BigDecimal(random.nextInt(300)))
                            .setChannel(channelId).setChips(uuidString).setVungoRechargeId(vungoRechargeId)
                            .setGaid(gaid).setOriginChannel(originChannelId).setDay(1L).setRegDate(dateLong)
                            .setOrder(uuidString).setCts(currentTimeMillis).setPn(pn.name()).setMtime(null)
                            .setLoginChannel(channelId).setRegisterChannel(channelId);

                    // suyh - 在flink 中跑null 指针异常了，不知道这几个数据是不是应该非NULL
                    // rechargeDate regDate 必须满足格式：yyyyMMdd
                    entity.setRegDate(dateLong).setStatRegDate(dateLong).setRechargeDate(dateLong);
                    entities.add(entity);
                }
            }

            log.trace("make recharge entity each size: {}, by ad user entity id: {}", size, adUserEntity.getId());
        }

        return entities;
    }

    private List<AdjustUserEntity> queryAdUserEntities(Page<AdjustUserEntity> page) {
        LambdaQueryWrapper<AdjustUserEntity> queryWrapper = new LambdaQueryWrapper<>();

        Page<AdjustUserEntity> entityPage = adjustUserMapper.selectPage(page, queryWrapper);
        return entityPage.getRecords();
    }
}
