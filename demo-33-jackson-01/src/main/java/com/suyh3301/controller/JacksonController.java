package com.suyh3301.controller;

import com.suyh3301.entity.JacksonEntity;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(value = "JacksonController", tags = {"web jackson 序列化与反序列化"})
@RestController
@Slf4j
public class JacksonController {

    @RequestMapping(value = "/jackson/01", method = RequestMethod.POST)
    public JacksonEntity jackson01(@RequestBody(required = false) JacksonEntity entity) {
        log.info("entity: {}", entity);
        JacksonEntity responseEntity = new JacksonEntity();
        responseEntity.setName("suyh");
        responseEntity.setAge(35);
        responseEntity.setBirth(new Date());
        return responseEntity;
    }

}
