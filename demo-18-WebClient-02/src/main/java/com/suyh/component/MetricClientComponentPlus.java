package com.suyh.component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;

/**
 * 并发请求
 */
@Component
@Slf4j
public class MetricClientComponentPlus {
    private final WebClient client01;
    private final WebClient client02;
    private final WebClient client03;
    private final WebClient client04;

    // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public MetricClientComponentPlus(WebClient.Builder builder) {
        this.client01 = builder.baseUrl("http://arms-dev.hissit.com").build();
        this.client02 = builder.baseUrl("http://arms-dev.hissit.com").build();
        this.client03 = builder.baseUrl("http://kweekshcct-kv7ex.com:52463").build();
        this.client04 = builder.baseUrl("http://kweekshcct-6edy6.com:52463").build();
    }

    @PostConstruct
    public void getMessage() {
        long nowMillis = System.currentTimeMillis();
        nowMillis = nowMillis - nowMillis % 1000;
        long startTime = nowMillis - 10 * 1000;
        long endTime = nowMillis;
        Mono<TempCommandResponse>  commandResponseMono01
                = this.client01.get()
                .uri(uriBuilder -> uriBuilder.path("/gov/app/demo/actuator/arms-command-api/metric")
                        .queryParam("startTime", "" + startTime)
                        .queryParam("endTime", "" + endTime)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TempCommandResponse.class);

        Mono<TempCommandResponse>  commandResponseMono02
                = this.client02.get()
                .uri(uriBuilder -> uriBuilder.path("/gov/app/demo/actuator/arms-command-api/metric")
                        .queryParam("startTime", "" + startTime)
                        .queryParam("endTime", "" + endTime)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TempCommandResponse.class);

        Mono<TempCommandResponse>  commandResponseMono03
                = this.client03.get()
                .uri(uriBuilder -> uriBuilder.path("/gov/app/demo/actuator/arms-command-api/metric")
                        .queryParam("startTime", "" + startTime)
                        .queryParam("endTime", "" + endTime)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TempCommandResponse.class);

        Mono<TempCommandResponse> commandResponseMono04
                = this.client04.get()
                .uri(uriBuilder -> uriBuilder.path("/gov/app/demo/actuator/arms-command-api/metric")
                        .queryParam("startTime", "" + startTime)
                        .queryParam("endTime", "" + endTime)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TempCommandResponse.class);

        // 将其合并到一起
        Flux<TempCommandResponse> mergeFlux = Flux.merge(
                commandResponseMono01, commandResponseMono02,
                commandResponseMono03, commandResponseMono04);

        mergeFlux.doOnNext(tempCommandResponse -> System.out.println("flux responseEntity: " + tempCommandResponse))
                .doOnError(exception -> System.out.println("exception: " + exception.getMessage()))
                // Schedulers.parallel() 返回一个线程池调度器，多次调用返回的是相同对象，所以它有缓存。
                .subscribeOn(Schedulers.parallel())
                // 异步调度
                .subscribe();

        System.out.println("nothing");
    }

    @Data
    private static class TempCommandResponse {
        private Boolean success;
        private String result;
        @JsonIgnore
        private Throwable exception;
    }
}
