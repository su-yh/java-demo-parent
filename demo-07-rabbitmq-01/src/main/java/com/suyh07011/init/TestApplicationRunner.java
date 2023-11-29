package com.suyh07011.init;

import com.suyh07011.controller.SendDemoController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2023-11-29
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TestApplicationRunner implements ApplicationRunner {
    private final SendDemoController sendDemoController;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendDemoController.sendTopicMessage1();
        sendDemoController.sendTopicMessage2();
    }
}
