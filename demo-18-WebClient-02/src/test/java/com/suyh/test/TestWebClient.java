package com.suyh.test;

import com.suyh.test.entity.Notice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestWebClient {

    @Test
    public void test01() throws InterruptedException {
        //创建客户端
        WebClient client = WebClient.create("http://localhost:11801");

        // subscribe() 就是提交，然后后台就会去自动触发HTTP 请求，这里显然是有一个线程在处理。
        // GET请求用户列表
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
                // 这里如果捕获到了，那么后面的subscribe 里面就不会被调用了。
                .onStatus(httpStatus -> httpStatus.equals(HttpStatus.NOT_FOUND), clientResponse -> Mono.error(new RuntimeException("error 404")))
                .onStatus(httpStatus -> httpStatus.equals(HttpStatus.INTERNAL_SERVER_ERROR), clientResponse -> Mono.error(new RuntimeException("error 500")))
                .bodyToFlux(Notice.class);
        noticePostFlux.subscribe(notice -> log.info("noticeFlux 10086: {}",
                ToStringBuilder.reflectionToString(notice, ToStringStyle.JSON_STYLE)), error -> log.error("error", error));

        TimeUnit.SECONDS.sleep(10);
        log.info("finished.");
    }

    /**
     * Flux 的调用是异常的，不会阻塞当前线程，所以需要用一个策略去让程序继续 执行
     *
     * @throws InterruptedException
     */
    @Test
    public void testFluxGetHttp01() throws InterruptedException {
        //创建客户端
        WebClient client = WebClient.create("http://localhost:11801");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        // subscribe() 就是提交，然后后台就会去自动触发HTTP 请求，这里显然是有一个线程在处理。
        // Notice.class 为响应的返回类型实体定义
        Flux<Notice> noticeFlux = client.get().uri("/impl/get/info").retrieve().bodyToFlux(Notice.class);
        // 添加最终处理，不管正常还是异常都会调用
        noticeFlux.doFinally(signalType -> countDownLatch.countDown())
                .subscribe(
                        // 正常结束，处理返回值
                        // 这里的正常结束是返回值为 200的情况，其他的情况都没有处理。
                        notice -> log.info("noticeFlux success, response entity: {}",
                                ToStringBuilder.reflectionToString(notice, ToStringStyle.JSON_STYLE)),
                        // 出异常时调用
                        exception -> {

                            log.error("noticeFlux exception", exception);
                        },
                        // 正常完成
                        // 如果异常，则没有调用
                        () -> {
                            log.info("noticeFlux complete.");
                        });

        countDownLatch.await();
        log.info("noticeFlux is finished.");
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Mono 的请求是阻塞线程，立即调用，直到调用响应返回结果为止.
     * 但是这样有一个问题就是当出现访问报错的情况就不好处理。
     */
    @Test
    public void testMonoGetHttp01() {
        //创建客户端
        WebClient client = WebClient.create("http://localhost:11801");

        // Notice.class 为响应的返回类型实体定义
        Mono<Notice> noticeMono = client.get().uri("/impl/get/info").retrieve().bodyToMono(Notice.class);
        Notice resultEntity = noticeMono.block();   // 这里会阻塞当前线程，直到请求返回结果
        log.info("noticeMono success, response entity: {}",
                ToStringBuilder.reflectionToString(resultEntity, ToStringStyle.JSON_STYLE));
    }

    @Test
    public void testGetHttp() {
        //创建客户端
        WebClient client = WebClient.create("http://localhost:11801/");

        // Notice.class 为响应的返回类型实体定义
        ClientResponse clientResponse = client.get().uri("/impl/get/info").exchange().block();
        HttpStatus httpStatus = clientResponse.statusCode();
        if (httpStatus.equals(HttpStatus.OK)) {
            // 这里的结果只能拿一次，要么使用Mono，要么使用Flux，否则会报异常的
            if (true) {
                Flux<Notice> resultEntityFlux = clientResponse.bodyToFlux(Notice.class);
                Notice noticeEntityFlux = resultEntityFlux.blockFirst();
                log.info("noticeMono success, response resultEntityFlux: {}",
                        ToStringBuilder.reflectionToString(noticeEntityFlux, ToStringStyle.JSON_STYLE));
            } else {
                Mono<Notice> resultEntityMono = clientResponse.bodyToMono(Notice.class);
                Notice noticeEntityMono = resultEntityMono.block();
                log.info("noticeMono success, response resultEntityMono: {}",
                        ToStringBuilder.reflectionToString(noticeEntityMono, ToStringStyle.JSON_STYLE));
            }
        } else {
            log.error("noticeMono failed, HttpStatus: {}", httpStatus);
        }
    }

    @Test
    public void testPostHttp() throws InterruptedException {
        Notice noticeEntity = new Notice();
        noticeEntity.setStatus(10086);
        Flux<Notice> noticePostFlux = WebClient.create("http://localhost:11801").post().uri("/impl/post/info/entity")
                // 给出请求body 的值
                .bodyValue(noticeEntity).retrieve()
                // 对异常状态的处理，对于状态为200 的不需要处理。如果有多个，则链式写法就可以了
                // 这里如果捕获到了，那么后面的subscribe 里面就不会被调用了。
                .onStatus(httpStatus -> httpStatus.equals(HttpStatus.NOT_FOUND), clientResponse -> Mono.error(new RuntimeException("error 404")))
                .onStatus(httpStatus -> httpStatus.equals(HttpStatus.INTERNAL_SERVER_ERROR), clientResponse -> Mono.error(new RuntimeException("error 500")))
                .bodyToFlux(Notice.class);
        noticePostFlux.subscribe(notice -> log.info("noticeFlux 10086: {}",
                ToStringBuilder.reflectionToString(notice, ToStringStyle.JSON_STYLE)), error -> log.error("error", error));

        log.info("http request finished.");
        TimeUnit.SECONDS.sleep(4);
    }
}
