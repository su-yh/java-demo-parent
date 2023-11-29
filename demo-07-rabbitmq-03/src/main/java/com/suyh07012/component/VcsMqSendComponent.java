package com.suyh07012.component;

import com.suyh07012.config.VcsConstants;
import com.suyh07012.vo.WaitingRecallUsersVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author suyh
 * @since 2023-11-29
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class VcsMqSendComponent implements ApplicationRunner {
    private final RabbitTemplate rabbitTemplate;

    public void sendTopicMessage1() {
        WaitingRecallUsersVo vo  = new WaitingRecallUsersVo();
        vo.setPn("pn").setPnum("pnum").setType(1).setPhysical(new BigDecimal("111")).setVirtual(new BigDecimal("222"))
                        .setPriority(12).setLink("https://www.baidu.com");
        rabbitTemplate.convertAndSend(VcsConstants.Mq.TOPIC_EXCHANGE, VcsConstants.Mq.TOPIC_RECALL_PLAYER_WAITING, vo);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendTopicMessage1();
    }
}
