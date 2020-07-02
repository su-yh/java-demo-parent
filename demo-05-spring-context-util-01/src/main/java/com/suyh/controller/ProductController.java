package com.suyh.controller;

import com.suyh.domain.Product;
import com.suyh.test.Normal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// 要使用 @RestController 注解，需要加入WEB 项目的坐标
@RestController
public class ProductController {
    @RequestMapping("/normal")
    public List<Product> normal() {
        Normal normal = new Normal();
        return normal.listProduct();
    }
}
