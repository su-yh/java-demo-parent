package com.suyh.service;

import com.suyh.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> listProduct();
    Product findById(int id);
}
