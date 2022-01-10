package com.suyh5101;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    public String doService(String content) {
        log.info("doService");
        return content;
    }
}
