package com.suyh07012.config;

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
public class TopicRabbitConfig {
    // 绑定键
    public final static String TOPIC_MAN = "topic.man";
    public final static String EXCHANGE = "topicExchange";

    @Bean
    public Queue firstQueue() {
        return new Queue(TopicRabbitConfig.TOPIC_MAN);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TopicRabbitConfig.EXCHANGE);
    }

    // 将firstQueue和topicExchange 绑定，而且绑定的键值为topic.man
    // 这样只要是消息携带的路由键是topic.man，才会分发到该队列
    @Bean
    public Binding bindingExchangeMessage(
            @Qualifier("firstQueue") Queue firstQueue,
            TopicExchange exchange) {
        return BindingBuilder.bind(firstQueue).to(exchange).with(TopicRabbitConfig.TOPIC_MAN);
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
