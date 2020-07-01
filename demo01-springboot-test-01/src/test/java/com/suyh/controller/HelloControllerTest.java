package com.suyh.controller;

import com.suyh.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(value = SpringRunner.class)
@SpringBootTest(
        // 这里是启动类吧
        classes = Application.class,
        // 使用默认端口
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloControllerTest {
    /**
     * 这种方式，在这里是没有问题的。但是我在其他地方使用的时候似乎有问题。
     */
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private HelloController helloController;

    // get 请求
    @Test
    public void getHi() {
        String hi = restTemplate.getForObject("/hello/hi?name=suyh01", String.class);
        Assert.assertEquals("hello suyh01", hi);
    }

    // post 请求
    @Test
    public void postHi() {
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("name", "suyh02");
        String hi = restTemplate.postForObject("/hello/hi", multiValueMap, String.class);
        Assert.assertEquals("hello suyh02", hi);
    }

    @Test
    public void testCallHi() {
        String name = "aaabbcdsuyh";
        String hiResult = helloController.hi(name);

        Assert.assertEquals("hello " + name, hiResult);
    }
}
