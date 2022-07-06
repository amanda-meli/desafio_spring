package com.example.desafiospring.service;

import com.example.desafiospring.dto.ProductDto;

import java.util.ArrayList;

public interface ProductService {
    void createProducts(ArrayList<ProductDto> products);
    ArrayList<ProductDto> findByCategory(String category);
    ArrayList<ProductDto> findByAlphabeticOrder();
    ProductDTO findByMinPrice();
    ProductDTO findByMaxPrice();

}
