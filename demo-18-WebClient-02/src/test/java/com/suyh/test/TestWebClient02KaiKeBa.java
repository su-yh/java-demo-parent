package com.suyh.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 开课吧老师教材中的示例学习demo
 */
@Slf4j
public class TestWebClient02KaiKeBa {

    class Student {

    }
    WebClient client = WebClient.create("http://localhost:8888/student");

    @Test
    public void test01() {

        Flux<Student> studentFlux = client.get().uri("/all")
                .retrieve() // 提交请求
                .bodyToFlux(Student.class);// 接收服务端的返回值，将其封闭到一个实体对象中。
    }

    @Test
    public void test02() {
        Student student = new Student();
        Mono<Student> studentMono = client.post()
                .uri("/save")
                .body(Mono.just(student), Student.class) // 请求体
                .retrieve() // 提交请求
                .bodyToMono(Student.class);
        studentMono.subscribe();    // 有可能需要添加订阅，可能 跟版本有关系吧
    }

    @Test
    public void test03() {
        String id = "idValue";
        Mono<Void> studentMono = client.delete()
                .uri("/delcomm/{id}", id)
                .retrieve() // 提交请求
                .bodyToMono(Void.class);
        studentMono.subscribe();    // 有可能需要添加订阅，可能 跟版本有关系吧
    }
}
