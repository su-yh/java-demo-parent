package com.suyh5504.websocket.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication5504 {
    public static void main(String[] args) {
        System.setProperty("server.port", "5504");
        SpringApplication.run(ServerApplication5504.class, args);
    }
}
