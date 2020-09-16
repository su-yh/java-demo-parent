//package com.suyh.controller;
//
//import com.suyh.entity.DemoEntityUser;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//@RequestMapping("/filter")
//@Slf4j
//public class FilterController {
//
//    @GetMapping("/name")
//    public String getName(@RequestParam("name") String name) {
//        log.info("name: {}", name);
//        return "filter: " + name;
//    }
//
//    @GetMapping("/entity")
//    public DemoEntityUser getEntity() {
//        log.info("getEntity");
//        DemoEntityUser res = new DemoEntityUser();
//        res.setName("suyh");
//        res.setAge(10);
//        res.setCreateTime(new Date());
//
//        return res;
//    }
//}
