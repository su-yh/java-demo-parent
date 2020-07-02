package cn.wolfcode.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wolfcode-lanxw
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }
}
