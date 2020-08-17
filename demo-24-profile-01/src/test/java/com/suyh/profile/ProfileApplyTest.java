package com.suyh.profile;

import com.suyh.ProfileApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProfileApplication.class)
@Slf4j
public class ProfileApplyTest {
    @Value("${spring.profiles.active}")
    private String activeValue;
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;
    @Value("${spring.redis.port}")
    private String redisPort;

    @Test
    public void test01() {
        log.info("spring.profiles.active: {}", activeValue);
        log.info("spring.datasource.url: {}", dataSourceUrl);
        log.info("spring.redis.port: {}", redisPort);
    }
}
