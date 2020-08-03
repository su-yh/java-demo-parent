package com.suyh.test;

import com.suyh.MockApplication;
import com.suyh.service.ServiceBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class TestMock {
    @SpyBean
    private ServiceBean serviceBean;

    @Test
    public void test01() {

    }
}
