package com.suyh;

import com.suyh.entity.DataBean;
import com.suyh.entity.Notice;
import com.suyh.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

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
     * 带参数(实体参数)
     * TODO: 参数不对呀，这个参数要怎么传呢
     */
    @Test
    public void testGetForObject03() {
        String uri = "/impl/get/info/entity";
        String url = host + uri;

        DataBean reqEntity = makeReqEntity();
        String reqParam = JsonUtil.serializable(reqEntity);
        log.info("reqParam: {}", reqParam);
        Notice resEntity = restTemplate.getForObject(url, Notice.class, reqParam);

        log.info("response entity: {}", resEntity);
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
