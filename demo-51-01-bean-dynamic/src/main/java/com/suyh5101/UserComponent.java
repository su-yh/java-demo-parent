package com.suyh5101;

import org.springframework.beans.factory.InitializingBean;

public class UserComponent implements InitializingBean {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是动态注册的你,不是容器启动的时候注册的你");
    }

    public String toAction(String content){
        return "-->" +  userService.doService(content);
    }

}
