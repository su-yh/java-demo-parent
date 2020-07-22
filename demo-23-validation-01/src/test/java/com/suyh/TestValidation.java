package com.suyh;

import com.suyh.entity.ChildEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {ValidationApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class TestValidation {
    @Resource
    private TestRestTemplate restTemplate;

    private static final String PRE_URL = "http://localhost:8080";

    @Test
    public void test01() {
        String url = PRE_URL + "/get/child/entity";
        ChildEntity childEntity = new ChildEntity();
        try {
            ResponseEntity<ChildEntity> responseChildEntity
                    = restTemplate.postForEntity(url, childEntity, ChildEntity.class);
            log.info("responseChildEntity: {}", ToStringBuilder.reflectionToString(
                    responseChildEntity, ToStringStyle.JSON_STYLE));
            Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), responseChildEntity.getStatusCodeValue());
        } catch (RestClientException ex) {
            log.error("exception", ex);
            Assert.fail("failed");
        } catch (Exception ex) {
            log.error("other exception", ex);
            Assert.fail("other exception");
        }
    }

    @Test
    public void test02() {
        String url = PRE_URL + "/get/child/entity";
        ChildEntity childEntity = new ChildEntity();
        // 小于1000 异常
        childEntity.setId(999);

        ResponseEntity<ChildEntity> responseChildEntity
                = restTemplate.postForEntity(url, childEntity, ChildEntity.class);
        log.info("responseChildEntity: {}", ToStringBuilder.reflectionToString(
                responseChildEntity, ToStringStyle.JSON_STYLE));
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), responseChildEntity.getStatusCodeValue());

        // 大于 9999异常
        ChildEntity childEntity02 = new ChildEntity();
        childEntity02.setId(10000);

        ResponseEntity<ChildEntity>  responseChildEntity02
                = restTemplate.postForEntity(url, childEntity02, ChildEntity.class);
        log.info("responseChildEntity: {}", ToStringBuilder.reflectionToString(
                responseChildEntity02, ToStringStyle.JSON_STYLE));
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), responseChildEntity02.getStatusCodeValue());

        // 1000 9999 正常范围，以及所有的值都是正常范围
        ChildEntity childEntity03 = new ChildEntity();
        childEntity03.setId(9999);
        childEntity03.setName("name");
        childEntity03.setDesc("desc");

        ResponseEntity<ChildEntity>  responseChildEntity03
                = restTemplate.postForEntity(url, childEntity03, ChildEntity.class);
        log.info("responseChildEntity: {}", ToStringBuilder.reflectionToString(
                responseChildEntity03, ToStringStyle.JSON_STYLE));
        Assert.assertNotEquals(HttpStatus.BAD_REQUEST.value(), responseChildEntity03.getStatusCodeValue());
        Assert.assertEquals(HttpStatus.OK.value(), responseChildEntity03.getStatusCodeValue());
    }

    @Test
    public void test03() {
        String url = PRE_URL + "/get/name";
        ResponseEntity<String> resp = restTemplate.getForEntity(
                url, String.class);
        String jsonResp = ToStringBuilder.reflectionToString(resp, ToStringStyle.JSON_STYLE);
        log.info("jsonResp: {}", jsonResp);
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), resp.getStatusCodeValue());
    }
}
