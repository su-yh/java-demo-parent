package com.suyh6003.controller;

import lombok.Data;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author suyh
 * @since 2023-11-05
 */
@RestController
@RequestMapping("/google/auth/code")
public class GoogleAuthCodeController {
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
            @RequestParam(value = "scope", required = false) String scope,
            @RequestParam(value = "code", required = false) String code) {
        System.out.println("重定向到这里来了。");
        System.out.println("state: " + state);
        System.out.println("accessToken: " + accessToken);
        System.out.println("tokenType: " + tokenType);
        System.out.println("expiresIn: " + expiresIn);
        System.out.println("scope: " + scope);
        System.out.println("code: " + code);

        System.out.println("tempDto: " + tempDto);
        return "OK";
    }


    /**
     * 参考一下 <a href="https://developers.google.com/identity/protocols/oauth2/web-server?hl=zh-cn">适用于服务端的web 应用</a>
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void googleAuthByToken(HttpServletResponse response) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder("https://accounts.google.com/o/oauth2/v2/auth");
        uriBuilder.addParameter("scope", "https://www.googleapis.com/auth/drive.metadata.readonly https://www.googleapis.com/auth/userinfo.email")
                // 指示当用户不在浏览器时，您的应用能否刷新访问令牌。有效的参数值包括 online（默认值）和 offline。
                // 如果您的应用需要在用户不在浏览器前刷新访问令牌，请将该值设为 offline。这是刷新访问令牌的方法，本文档后面部分将对其进行介绍。此值指示 Google 授权服务器在您的应用首次用授权代码交换令牌时返回刷新令牌和访问令牌。
                .addParameter("access_type", "offline")
                // 当response_type 为token 时 access_type 不能是offline
                .addParameter("response_type", "code")  // 返回的授权码类型，这里是code。也可以是token
                .addParameter("include_granted_scopes", "true") // 增量授权，即获得用户以前给予的所有权限
                .addParameter("state", "state_parameter_passthrough_value") // 用来维护授权请求和授权服务器的响应之间的状态的任何字符串值
                // 确定用户完成授权流程后api 服务器将用户重定向到的位置
                .addParameter("redirect_uri", "http://localhost:8080/suyh/google/auth/callback")
                .addParameter("client_id", "159535634406-fjk2tggbls08ean0e0rruh51rlbkffr2.apps.googleusercontent.com");

        URI uri = uriBuilder.build();
        System.out.println("uri: " + uri.toString());
        response.sendRedirect(uri.toString());
    }
}
