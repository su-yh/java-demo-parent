package com.suyh.test;

import com.suyh.test.entity.Notice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.http.HttpStatus;
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

        //GET请求id为1的用户
        Mono<Notice> user = client.get().uri("/impl/get/info").retrieve().bodyToMono(Notice.class);
        //强制阻塞，否则看不到输出
        Notice noticeResult = user.block();
        log.info("noticeFlux 03: {}", ToStringBuilder.reflectionToString(noticeResult, ToStringStyle.JSON_STYLE));

        // post 请求
        Notice noticeEntity = new Notice();
        noticeEntity.setStatus(10086);
        Flux<Notice> noticePostFlux = client.post().uri("/impl/post/info/entity")
                .bodyValue(noticeEntity).retrieve()
                // 对异常状态的处理，对于状态为200 的不需要处理。如果有多个，则链式写法就可以了
                .onStatus(httpStatus -> !httpStatus.equals(HttpStatus.OK), clientResponse -> Mono.error(new RuntimeException("error")))
                .onStatus(httpStatus -> httpStatus.equals(HttpStatus.OK), clientResponse -> Mono.error(new RuntimeException("success")))
                .bodyToFlux(Notice.class);
        noticePostFlux.subscribe(notice -> log.info("noticeFlux 10086: {}",
                ToStringBuilder.reflectionToString(notice, ToStringStyle.JSON_STYLE)), error -> log.error("error", error));

        TimeUnit.SECONDS.sleep(10);
    }
}
