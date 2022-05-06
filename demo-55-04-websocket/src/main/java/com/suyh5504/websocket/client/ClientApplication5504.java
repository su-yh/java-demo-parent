package com.suyh5504.websocket.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication5504 {
    public static void main(String[] args) {
        System.setProperty("server.port", "15504");
        SpringApplication.run(ClientApplication5504.class, args);
    }
}
