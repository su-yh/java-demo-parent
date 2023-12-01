package com.suyh07013.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2023-11-29
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitConfig implements RabbitListenerConfigurer {
    private final MessageHandlerMethodFactory factory;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(factory);
    }
}
