package com.suyh;

import com.suyh.entity.DataBean;
import com.suyh.entity.Notice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationRestTemplate01.class},
        // 使用此参数的主要目的是要让 TestRestTemplate 正常且有效
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class DemoHttpRequestTest {
    @Resource
    private TestRestTemplate restTemplate;

    @Value("http://localhost:${server.port}")
    private String preUrl;

    /**
     * 不带参数的get 请求
     */
    @Test
    public void testGetForObject01() {
        String uri = "/impl/get/info";
        String url = preUrl + uri;
        Notice resEntity = restTemplate.getForObject(url, Notice.class);

        log.info("response entity: {}", resEntity);
    }

    /**
     * 带参数(@RequestParam)的get 请求
     */
    @Test
    public void testGetForObject02() {
        String uri = "/impl/get/info/param";
        String url = preUrl + uri + "?id=1234&param=param-value";
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
        String url = preUrl + uri;
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

    // 这种方式比较好，可读性好，它是由spring提供的，
    @Test
    public void tttt() {
        {
            // 以{name} 进行占位，后面按顺序补充路径参数
            String url = "http://test.com/solarSystem";

            // Query parameters
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                    // Add query parameter
                    .queryParam("firstName", "Mark")
                    .queryParam("lastName", "Watney")
                    // 按顺序给路径参数
                    // 这是直接往uri 上添加路径，比如uri是: http://test.com/solarSystem，添加如下代码，则uri会补充为：http://test.com/solarSystem/Mars/Phobos
                    // 所以在url 上面就不用写完整路径了，只需要写到域名即可。
                    .pathSegment("Mars", "Phobos"); // 还有另外一种方式 ：URIBuilder

            // 如果没有路径参数就直接构建就可以了。
            URI uri1 = builder.build().toUri();

        }

        {
            /*
             * Console output:
             * http://test.com/solarSystem/planets/Mars/moons/Phobos?firstName=Mark&lastName=Watney
             */
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<Notice> reqEntity = new HttpEntity<>(new Notice(), headers);

            // 路径参数
            String url = "http://test.com/solarSystem/planets/{planet}/moons/{moon}";
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

            Map<String, String> pathParams = new HashMap<>();
            pathParams.put("planet", "Mars");
            pathParams.put("moon", "Phobos");
            URI uri = builder.buildAndExpand(pathParams).toUri();
            // 返回值类型可以指定一个class
            final ResponseEntity<String> responseEntity = restTemplate.exchange(
                    uri, HttpMethod.PUT, reqEntity, String.class);
            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                return;
            }

            final String responseEntityBody = responseEntity.getBody();
        }
    }

    // 这种方式不好，可读性差，它是由apache 提供的，
    @Test
    @Deprecated
    public void tttt22() throws URISyntaxException {
//        String url = "http://test.com/solarSystem/planets/{planet}/moons/{moon}";
        String url = "http://test.com";

        // Query parameters
        URIBuilder uriBuilder = new URIBuilder(url);
        // 这里就是不太好，可读性比较差。还是使用spring 的UriComponentsBuilder 比较好读一些。
        // 这些就是会按这些参数的顺序依次补充到url 路径上面，并添加 "/"
        uriBuilder.setPathSegments("solarSystem", "planets", "Mars", "moons", "Phobos")
                // Add query parameter
                .addParameter("firstName", "Mark")
                .addParameter("lastName", "Watney");

        URI uri = uriBuilder.build();

        System.out.println("uri: " + uri);
    }

    @Test
    public void test002() throws URISyntaxException {
        URI uri = new URIBuilder().setScheme("http").setHost("127.0.0.1")
                .setPort(8080)
                .setPath("/resource/instance/info")
                .addParameter("uuid", "uuid-value")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        // 指定body 的类型为Notice 以及具体的值，并添加请求头的值
        HttpEntity<Notice> httpEntity = new HttpEntity<>(new Notice(), headers);
        // 指定返回值类型为DataBean
        final ResponseEntity<DataBean> rspEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, DataBean.class);
        if (!rspEntity.getStatusCode().is2xxSuccessful()) {
            log.warn("failed.");
            return;
        }
        DataBean resultVo = rspEntity.getBody();
        if (resultVo == null) {
            log.error("result vo is null");
            return;
        }
    }

    /**
     * 随便生成一个请求参数
     *
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
