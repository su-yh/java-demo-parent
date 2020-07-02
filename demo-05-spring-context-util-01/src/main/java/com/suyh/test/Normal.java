package com.suyh.test;

import com.suyh.domain.Product;
import com.suyh.service.ProductService;
import com.suyh.util.SpringContextUtil;

import java.util.List;

/**
 * 一个普通类访问 spring bean 对象
 */
public class Normal {

    public List<Product> listProduct() {
        ProductService productService = SpringContextUtil.getBean(ProductService.class);
        List<Product> products = productService.listProduct();
        System.out.println(products);
        return products;
    }
}
