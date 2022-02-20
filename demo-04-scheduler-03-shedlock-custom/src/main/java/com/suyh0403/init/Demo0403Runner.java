package com.suyh0403.init;

import com.suyh0403.component.CustomShedlock;
import com.suyh0403.component.CustomShedlockPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Demo0403Runner implements ApplicationRunner {
    @Resource
    private CustomShedlock customShedlock;

    @Resource
    private CustomShedlockPlus customShedlockPlus;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testBaseShedlock();
        testPlusShedlock();
    }

    private void testBaseShedlock() throws InterruptedException {
        Executor executor = Executors.newScheduledThreadPool(20);
        for (int i = 0; i < 20; i++) {
            log.info("第{}个开始", i + 1);
            executor.execute(() -> customShedlock.executeRegistryWithLock("appname-base"));
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private void testPlusShedlock() throws InterruptedException {
        Executor executor = Executors.newScheduledThreadPool(20);
        for (int i = 0; i < 20; i++) {
            log.info("第{}个开始", i + 1);
            executor.execute(() -> {
                boolean res = customShedlockPlus.executeRegistryWithLock("appname-plus");
                log.info("plus execute result: {}", res);
            });

            TimeUnit.SECONDS.sleep(1);
        }
    }
}
