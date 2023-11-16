package com.suyh;

import com.suyh2201.ValidationApplication;
import com.suyh2201.entity.ChildEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
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
        Assertions.assertDoesNotThrow(() -> {
            try {
                ResponseEntity<ChildEntity> responseChildEntity
                        = restTemplate.postForEntity(url, childEntity, ChildEntity.class);
                log.info("responseChildEntity: {}", ToStringBuilder.reflectionToString(
                        responseChildEntity, ToStringStyle.JSON_STYLE));
                Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseChildEntity.getStatusCodeValue());
            } catch (RestClientException ex) {
                log.error("exception", ex);
                throw ex;
            } catch (Exception ex) {
                log.error("other exception", ex);
                throw ex;
            }
        });
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
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseChildEntity.getStatusCodeValue());

        // 大于 9999异常
        ChildEntity childEntity02 = new ChildEntity();
        childEntity02.setId(10000);

        ResponseEntity<ChildEntity>  responseChildEntity02
                = restTemplate.postForEntity(url, childEntity02, ChildEntity.class);
        log.info("responseChildEntity: {}", ToStringBuilder.reflectionToString(
                responseChildEntity02, ToStringStyle.JSON_STYLE));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseChildEntity02.getStatusCodeValue());

        // 1000 9999 正常范围，以及所有的值都是正常范围
        ChildEntity childEntity03 = new ChildEntity();
        childEntity03.setId(9999);
        childEntity03.setName("name");
        childEntity03.setDesc("desc");

        ResponseEntity<ChildEntity>  responseChildEntity03
                = restTemplate.postForEntity(url, childEntity03, ChildEntity.class);
        log.info("responseChildEntity: {}", ToStringBuilder.reflectionToString(
                responseChildEntity03, ToStringStyle.JSON_STYLE));
        Assertions.assertNotEquals(HttpStatus.BAD_REQUEST.value(), responseChildEntity03.getStatusCodeValue());
        Assertions.assertEquals(HttpStatus.OK.value(), responseChildEntity03.getStatusCodeValue());
    }

    @Test
    public void test03() {
        String url = PRE_URL + "/get/name";
        ResponseEntity<String> resp = restTemplate.getForEntity(
                url, String.class);
        String jsonResp = ToStringBuilder.reflectionToString(resp, ToStringStyle.JSON_STYLE);
        log.info("jsonResp: {}", jsonResp);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), resp.getStatusCodeValue());
    }
}
