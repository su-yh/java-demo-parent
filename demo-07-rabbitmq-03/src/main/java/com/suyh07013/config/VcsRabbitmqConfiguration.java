package com.suyh07013.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suyh
 * @since 2023-11-29
 */
@Configuration
public class VcsRabbitmqConfiguration {
    @Bean
    public Queue recallSuccessQueue() {
        return new Queue(VcsConstants.Mq.TOPIC_RECALL_PLAYER_SUCCESS);
    }

    @Bean
    public Queue recallWaitingQueue() {
        return new Queue(VcsConstants.Mq.TOPIC_RECALL_PLAYER_WAITING);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(VcsConstants.Mq.TOPIC_EXCHANGE);
    }

    // 将 recallSuccessQueue 和aiteer-vcs-exchange 绑定，而且绑定的键值为VcsConstants.Mq.TOPIC_RECALL_PLAYER_SUCCESS
    // 这样只要是消息携带的路由键是VcsConstants.Mq.TOPIC_RECALL_PLAYER_SUCCESS，才会分发到该队列
    @Bean
    public Binding bindingExchangeMessageRecallSuccess(
            @Qualifier("recallSuccessQueue") Queue recallSuccessQueue,
            TopicExchange exchange) {
        return BindingBuilder.bind(recallSuccessQueue).to(exchange).with(VcsConstants.Mq.TOPIC_RECALL_PLAYER_SUCCESS);
    }

    // 将 recallWaitingQueue 和aiteer-vcs-exchange 绑定，而且绑定的键值为VcsConstants.Mq.TOPIC_RECALL_PLAYER_WAITING
    // 这样只要是消息携带的路由键是VcsConstants.Mq.TOPIC_RECALL_PLAYER_WAITING，才会分发到该队列
    @Bean
    public Binding bindingExchangeMessageRecallWaiting(
            @Qualifier("recallWaitingQueue") Queue recallWaitingQueue,
            TopicExchange exchange) {
        return BindingBuilder.bind(recallWaitingQueue).to(exchange).with(VcsConstants.Mq.TOPIC_RECALL_PLAYER_WAITING);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @ConditionalOnMissingBean(Jackson2JsonMessageConverter.class)
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
