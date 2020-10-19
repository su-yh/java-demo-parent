package com.suyh.test;

import com.suyh.test.entity.Notice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestWebClient {

    @Test
    public void test01() throws InterruptedException {
        //创建客户端
        WebClient client = WebClient.create("http://localhost:8080");

        // subscribe() 就是提交，然后后台就会去自动触发HTTP 请求，这里显然是有一个线程在处理。
        //GET请求用户列表
        Flux<Notice> noticeFlux = client.get().uri("/impl/get/info").retrieve().bodyToFlux(Notice.class);
        noticeFlux.subscribe(notice -> log.info("noticeFlux 01: {}",
                ToStringBuilder.reflectionToString(notice, ToStringStyle.JSON_STYLE)));
        Flux<Notice> noticeFlux2 = client.get().uri("/impl/get/info").retrieve().bodyToFlux(Notice.class);
        noticeFlux2.subscribe(notice -> log.info("noticeFlux 02: {}",
                ToStringBuilder.reflectionToString(notice, ToStringStyle.JSON_STYLE)));

//        //GET请求id为1的用户
//        Mono<Notice> user = client.get().uri("/impl/get/info").retrieve().bodyToMono(Notice.class);
//        //强制阻塞，否则看不到输出
//        Notice noticeResult = user.block();
//        log.info("noticeFlux 03: {}", ToStringBuilder.reflectionToString(noticeResult, ToStringStyle.JSON_STYLE));

        Notice noticeEntity = new Notice();
        noticeEntity.setStatus(10086);
        Flux<Notice> noticePostFlux = client.post().uri("/impl/post/info/entity")
                .bodyValue(noticeEntity).retrieve().bodyToFlux(Notice.class);
//        Flux<Notice> noticePostFlux = WebClient.create("http://localhost:8080/impl/post/info/entity").post()
//                .bodyValue(noticeEntity).retrieve().bodyToFlux(Notice.class);
        noticePostFlux.subscribe(notice -> log.info("noticeFlux 10086: {}",
                ToStringBuilder.reflectionToString(notice, ToStringStyle.JSON_STYLE)));
//        //POST
//        Mono<Notice> user = client.post().uri("/impl/post/info?status=4").retrieve().bodyToMono(Notice.class);
//        Notice noticeResult = user.block();
//        log.info("noticeFlux 04 post: {}", ToStringBuilder.reflectionToString(noticeResult, ToStringStyle.JSON_STYLE));
////        POST新增用户，Form提交方式
////        user = client.post().uri("/users").retrieve().bodyToMono(Notice.class);
////        System.out.println(user.block());
//        //获取响应信息
//        ClientResponse response = client.post().uri("/impl/post/info?status=5").exchange().block();
//        log.info("noticeFlux 05 post: {}", ToStringBuilder.reflectionToString(response, ToStringStyle.JSON_STYLE));
//        Mono<ResponseEntity<Notice>> entity = response.toEntity(Notice.class);
//        Mono<Notice> noticeMono = response.bodyToMono(Notice.class);

        TimeUnit.SECONDS.sleep(10);
    }

}
