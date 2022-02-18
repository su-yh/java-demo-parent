package com.suyh2301.annotation.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SmsAndMailSender {
    /* 短信模板String格式化串 */
    String value() default "";

    String smsContent() default "";

    String mailContent() default "";

    /*是否激活发送功能*/
    boolean isActive() default true;

    /*主题*/
    String subject() default "";
}
