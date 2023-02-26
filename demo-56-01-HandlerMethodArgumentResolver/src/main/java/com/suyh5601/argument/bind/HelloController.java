package com.suyh5601.argument.bind;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping
public class HelloController {

    @ResponseBody
    @GetMapping("/test/curruser")
    public Object testCurrUser(@CurrLoginUser CurrUserVo currUser) {
        return currUser;
    }
    @ResponseBody
    @GetMapping("/test/curruser/map")
    public Object testCurrUserMap(@CurrLoginUser Map<String,Object> currUser) {
        return currUser;
    }
    @ResponseBody
    @GetMapping("/test/curruser/object")
    public Object testCurrUserObject(@CurrLoginUser Object currUser) {
        return currUser;
    }
}
