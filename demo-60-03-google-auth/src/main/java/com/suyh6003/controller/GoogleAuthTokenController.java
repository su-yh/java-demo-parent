package com.suyh6003.controller;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/google/auth/token")
public class GoogleAuthTokenController {
    /**
     * 这个接口，就是前端调用之后，后台直接对接google 服务器，构造出一个由前端去重定向的一个google 登录地址。
     * 前端用户在界面上可以看到这个地址，然后就可以使用google 帐户进行登录了。
     * 登录成功之后，google 会重定向到下面我们给出的那个重定身地址，最终回到我们自己的应用。
     *
     * 然后就可以查询到该登录用户的google 帐 户信息了：
     * <a href="https://www.googleapis.com/oauth2/v2/userinfo?access_token=(access_token_value)">查询用户信息</a>
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public void googleAuthByCode(HttpServletResponse response) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder("https://accounts.google.com/o/oauth2/v2/auth");
        uriBuilder.addParameter("scope", "https://www.googleapis.com/auth/drive.metadata.readonly https://www.googleapis.com/auth/userinfo.email")
                // 指示当用户不在浏览器时，您的应用能否刷新访问令牌。有效的参数值包括 online（默认值）和 offline。
                // 如果您的应用需要在用户不在浏览器前刷新访问令牌，请将该值设为 offline。这是刷新访问令牌的方法，本文档后面部分将对其进行介绍。此值指示 Google 授权服务器在您的应用首次用授权代码交换令牌时返回刷新令牌和访问令牌。
                // .addParameter("access_type", "offline")
                .addParameter("access_type", "online")
                // 当response_type 为token 时 access_type 不能是offline
                // .addParameter("response_type", "code")  // 返回的授权码类型，这里是code。也可以是token
                .addParameter("response_type", "token")  // 返回的授权码类型，这里是code。也可以是token
                .addParameter("include_granted_scopes", "true") // 增量授权，即获得用户以前给予的所有权限
                .addParameter("state", "state_parameter_passthrough_value") // 用来维护授权请求和授权服务器的响应之间的状态的任何字符串值
                // 确定用户完成授权流程后api 服务器将用户重定向到的位置
                .addParameter("redirect_uri", "http://localhost:8080/suyh/suyh.html")
                .addParameter("client_id", "159535634406-fjk2tggbls08ean0e0rruh51rlbkffr2.apps.googleusercontent.com");

        URI uri = uriBuilder.build();
        response.sendRedirect(uri.toString());
    }
}
