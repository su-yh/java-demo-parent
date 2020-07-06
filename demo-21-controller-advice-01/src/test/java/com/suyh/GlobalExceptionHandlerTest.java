package com.suyh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {ControllerAdviceApp01.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class GlobalExceptionHandlerTest {

    @Resource
    private TestRestTemplate restTemplate;

    @Test
    public void test01() {
        String url = "http://localhost:8080/req/exception";

        String res = restTemplate.getForObject(url, String.class);
        log.info("result: " + res);
        Assert.assertEquals("error result", res);
    }

    @Test
    public void test02() {
        String url = "http://localhost:8080/req/runtime/exception";

        String res = restTemplate.getForObject(url, String.class);
        log.info("result: " + res);
        Assert.assertEquals("error runtime exception", res);
    }

    @Test
    public void test03() {
        String url = "http://localhost:8080/req/suyh/exception";

        String res = restTemplate.getForObject(url, String.class);
        log.info("result: " + res);
        Assert.assertEquals("suyh exception", res);
    }

    @Test
    public void test04() {
        String url = "http://localhost:8080/req/normal";

        String res = restTemplate.getForObject(url, String.class);
        log.info("result: " + res);
        Assert.assertEquals("no exception, result is ok.", res);
    }
}
