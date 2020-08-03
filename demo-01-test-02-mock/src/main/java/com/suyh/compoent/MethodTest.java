package com.suyh.compoent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MethodTest {
    //主方法
    public String gg(boolean flag) {
        log.info("coming.........");
        String d = g(flag);//子方法
        h();
        log.info("result data is " + d);
        return d;
    }

    public String g(boolean flag) {
        log.info("coming.........g");
        if (flag) {
            throw new IllegalAccessError();//flag为true时抛异常
        }
        return "d";
    }

    public void h() {
        log.info("coming.........h");
    }
}
