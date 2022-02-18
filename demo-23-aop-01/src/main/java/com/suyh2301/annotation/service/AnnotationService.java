package com.suyh2301.annotation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AnnotationService {
    @SmsAndMailSender(smsContent = "content value",
            mailContent = "nothing", subject = "subject")
    public void sayHello() {
        log.info("AnnotationService.sayHello");
    }
}
