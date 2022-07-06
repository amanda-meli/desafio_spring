package com.example.desafiospring.service;

import com.example.desafiospring.model.Product;

import java.util.ArrayList;

public interface IProductService {
    void createProducts(ArrayList<Product> products);
    ArrayList<Product> findByCategory(String category);
    ArrayList<Product> findByAlphabeticOrder();
    Product findByMinPrice();
    Product findByMaxPrice();
    ArrayList<Product> findByFreeShipping();
    ArrayList<Product> findByPrestige();
}
