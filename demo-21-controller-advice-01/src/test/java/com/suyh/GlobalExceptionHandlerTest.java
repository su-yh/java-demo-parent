package com.suyh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {ControllerAdviceApp01.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class GlobalExceptionHandlerTest {

    @Resource
    private TestRestTemplate restTemplate;

    @Value("http://localhost:${server.port}")
    private String domain;

    @Test
    public void test01() {
        String url = domain + "/req/exception";

        String res = restTemplate.getForObject(url, String.class);
        log.info("result: " + res);
        Assert.assertEquals("error result", res);
    }

    @Test
    public void test02() {
        String url = domain + "/req/runtime/exception";

        String res = restTemplate.getForObject(url, String.class);
        log.info("result: " + res);
        Assert.assertEquals("error runtime exception", res);
    }

    @Test
    public void test03() {
        String url = domain + "/req/suyh/exception";

        String res = restTemplate.getForObject(url, String.class);
        log.info("result: " + res);
        Assert.assertEquals("suyh exception", res);
    }

    @Test
    public void test04() {
        String url = domain + "/req/normal";

        String res = restTemplate.getForObject(url, String.class);
        log.info("result: " + res);
        Assert.assertEquals("no exception, result is ok.", res);
    }
}
