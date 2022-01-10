package com.suyh5101;

import org.springframework.beans.factory.InitializingBean;

public class UserArgsComponent implements InitializingBean {

    private final UserService userService;

    public UserArgsComponent(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是动态注册的你, 不是容器启动的时候注册的你。使用带参数的构造方法。");
    }

    public String toAction(String content){
        return "-->" +  userService.doService(content);
    }
}
