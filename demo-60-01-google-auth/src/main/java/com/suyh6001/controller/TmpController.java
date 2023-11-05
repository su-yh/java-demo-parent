package com.suyh6001.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suyh
 * @since 2023-11-05
 */
@RestController
@RequestMapping("/tmp")
public class TmpController {
    @Data
    public static class TempDto {
        private String state;
        private String access_token;
        private String token_type;
        private Integer expires_in;
        private String scope;
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String tmpCallback(
            TempDto tempDto,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "access_token", required = false) String accessToken,
            @RequestParam(value = "token_type", required = false) String tokenType,
            @RequestParam(value = "expires_in", required = false) Integer expiresIn,
            @RequestParam(value = "scope", required = false) String scope) {
        System.out.println("重定向到这里来了。");
        System.out.println("state: " + state);
        System.out.println("accessToken: " + accessToken);
        System.out.println("tokenType: " + tokenType);
        System.out.println("expiresIn: " + expiresIn);
        System.out.println("scope: " + scope);

        System.out.println("tempDto: " + tempDto);
        return "OK";
    }
}
