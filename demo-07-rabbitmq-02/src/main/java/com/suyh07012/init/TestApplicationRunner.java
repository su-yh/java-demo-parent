package com.suyh07012.init;

import com.suyh07012.config.TopicRabbitConfig;
import com.suyh07012.vo.TempVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author suyh
 * @since 2023-11-29
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TestApplicationRunner implements ApplicationRunner {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendTopicMessage1();
    }

    public void sendTopicMessage1() {
        TempVo vo  = new TempVo();
        vo.setMessageId(1L).setMessageData("message: M A N").setCreateTime(new Date());
        rabbitTemplate.convertAndSend(TopicRabbitConfig.EXCHANGE, TopicRabbitConfig.TOPIC_MAN, vo);
    }
}
