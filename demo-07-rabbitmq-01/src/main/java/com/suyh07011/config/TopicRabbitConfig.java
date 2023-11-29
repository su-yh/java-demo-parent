package com.suyh07011.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suyh
 * @since 2023-11-29
 */
@Configuration
public class TopicRabbitConfig {
    // 绑定键
    public final static String man = "topic.man";
    public final static String women = "topic.women";

    @Bean
    public Queue firstQueue() {
        return new Queue(TopicRabbitConfig.man);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(TopicRabbitConfig.women);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    // 将firstQueue和topicExchange 绑定，而且绑定的键值为topic.man
    // 这样只要是消息携带的路由键是topic.man，才会分发到该队列
    @Bean
    public Binding bindingExchangeMessage(
            @Qualifier("firstQueue") Queue firstQueue,
            TopicExchange exchange) {
        return BindingBuilder.bind(firstQueue).to(exchange).with(TopicRabbitConfig.man);
    }

    // 将secondQueue和topicExchange 绑定，而且绑定的键值为topic.#
    // 这样只要是消息携带的路由键是topic.开头，都会分发到该队列
    @Bean
    public Binding bindingExchangeMessage2(
            @Qualifier("secondQueue") Queue secondQueue,
            TopicExchange exchange) {
        return BindingBuilder.bind(secondQueue).to(exchange).with("topic.#");
    }
}
