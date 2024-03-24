package com.suyh0302.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyh0302.redis.MessagePublisher;
import com.suyh0302.redis.RedisMessagePublisher;
import com.suyh0302.redis.RedisMessageSubscriber;
import com.suyh0302.redis.vo.Student;
import com.suyh0302.util.JsonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration(proxyBeanMethods = false)
public class RedisConfiguration {
    @Bean
    public Jackson2JsonRedisSerializer<Student> jackson2JsonSerializer() {
        Jackson2JsonRedisSerializer<Student> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Student.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonUtil.initMapper(mapper);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);
        return jackson2JsonRedisSerializer;
    }

    @Bean
    public RedisTemplate<String, Student> redisCacheTemplate(
            LettuceConnectionFactory factory, Jackson2JsonRedisSerializer<Student> jackson2JsonSerializer) {
        RedisTemplate<String, Student> template = new RedisTemplate<>();
        template.setKeySerializer(StringRedisSerializer.UTF_8);
        template.setHashKeySerializer(StringRedisSerializer.UTF_8);
        template.setValueSerializer(jackson2JsonSerializer);
        template.setHashValueSerializer(jackson2JsonSerializer);
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean
    MessageListenerAdapter messageListener(RedisMessageSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber);
    }

    @Bean
    RedisMessageListenerContainer redisContainer(
            RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    @Bean
    MessagePublisher redisPublisher(RedisTemplate<String, Student> redisCacheTemplate) {
        return new RedisMessagePublisher(redisCacheTemplate);
    }
}
