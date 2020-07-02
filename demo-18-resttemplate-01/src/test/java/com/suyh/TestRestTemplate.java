package com.suyh;

import com.suyh.entity.DataBean;
import com.suyh.entity.Notice;
import com.suyh.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Slf4j
public class TestRestTemplate {
    private static RestTemplate restTemplate = new RestTemplate();

    private static String host = "http://localhost:8080";

    /**
     * 不带参数的get 请求
     */
    @Test
    public void testGetForObject01() {
        String uri = "/impl/get/info";
        String url = host + uri;
        Notice resEntity = restTemplate.getForObject(url, Notice.class);

        log.info("response entity: {}", resEntity);
    }

    /**
     * 带参数(@RequestParam)的get 请求
     */
    @Test
    public void testGetForObject02() {
        String uri = "/impl/get/info/param";
        String url = host + uri + "?id=1234&param=param-value";
        Notice resEntity = restTemplate.getForObject(url, Notice.class);

        log.info("response entity: {}", resEntity);
    }

    /**
     * 使用ForEntity 的方式调用，可以得到更多信息。
     * 一般我们可以处理请求返回状态值，用于校验。
     */
    @Test
    public void testGetForEntity01() {
        String uri = "/impl/get/info";
//        uri = "/impl/get/info/notfount";
        String url = host + uri;
        // 的确可以得到404，但是它直接抛了异常，那这样似乎没什么用了。只能捕获异常再做处理。
        ResponseEntity<Notice> resp = restTemplate.getForEntity(url, Notice.class);
        String jsonResp = ToStringBuilder.reflectionToString(resp, ToStringStyle.JSON_STYLE);
        log.info("jsonResp: {}", jsonResp);
        if (resp.getStatusCode() == HttpStatus.OK) {
            log.info("request OK");
        } else if (resp.getStatusCode() == HttpStatus.NOT_FOUND) {
            log.info("not found");
        }
    }

    /**
     * 随便生成一个请求参数
     * @return entity
     */
    private static DataBean makeReqEntity() {
        Random random = new Random();
        DataBean resp = new DataBean();
        resp.setNoticeId(random.nextInt(900));
        resp.setNoticeTitle("title");
        resp.setNoticeContent(UUID.randomUUID().toString());
        resp.setNoticeCreateTime(new Date());

        return resp;
    }
}
