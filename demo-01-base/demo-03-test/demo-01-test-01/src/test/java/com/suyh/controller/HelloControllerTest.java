package com.suyh.controller;

import com.suyh.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@ActiveProfiles("suyh") // 有时需要激活对应的环境，则使用此注解
@RunWith(value = SpringRunner.class)    // 这个是junit4 的方式，如果是junit5 使用 @ExtendWith(SpringExtension.class) 直接替代
@SpringBootTest(
        // 这里是启动类吧
        classes = Application.class,
        // 使用默认端口
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloControllerTest {
    /**
     * 这种方式，在这里是没有问题的。但是我在其他地方使用的时候似乎有问题。
     * 使用这种方式，需要指定 webEnvironment 的值，如上
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
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("name", "suyh02");
        String hi = restTemplate.postForObject("/hello/hi", multiValueMap, String.class);
        Assert.assertEquals("hello suyh02", hi);
    }

    @Test
    public void postHi02() {

        // headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.AUTHORIZATION, "Authorization");
        // HttpEntity body map
        // MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        // requestBody.add("bodyName", "suyhHeaders");
        // HttpEntity<MultiValueMap<String, String>> requestEntity
        //         = new HttpEntity<>(requestBody, requestHeaders);

        // HttpEntity body entity
        SecurityProperties.User user = new SecurityProperties.User();
        user.setName("suyh-postHi02");
        HttpEntity<SecurityProperties.User> requestEntity
                = new HttpEntity<>(user, requestHeaders);
        // post
        ResponseEntity<String> responseEntity
                // @RequestParam 通过路径参数传送
                = restTemplate.postForEntity("/hello/hi/headers?paramName=paramValue",
                requestEntity, String.class);

        System.out.println(responseEntity.getBody());
    }

    @Test
    public void testCallHi() {
        String name = "aaabbcdsuyh";
        String hiResult = helloController.hi(name);

        Assert.assertEquals("hello " + name, hiResult);
    }
}
