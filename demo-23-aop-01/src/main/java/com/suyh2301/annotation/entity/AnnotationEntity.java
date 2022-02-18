package com.suyh2301.annotation.entity;

import com.suyh2301.annotation.service.SmsAndMailSender;
import lombok.extern.slf4j.Slf4j;

/**
 * 试一下，一个普通实体类对象，是否可以使用AOP.
 *
 * 结果显示，没有用。没法做AOP
 */
@Slf4j
public class AnnotationEntity {

    @SmsAndMailSender
    public void sayHelloEntity() {
        log.info("AnnotationEntity.sayHelloEntity");
    }
}
