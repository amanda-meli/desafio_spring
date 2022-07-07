package com.example.desafiospring.service;

import com.example.desafiospring.dto.ProductDto;
import com.example.desafiospring.model.Product;

import java.util.List;
import java.util.ArrayList;

public interface IProductService {
    List<ProductDto> createProducts(ArrayList<Product> products);
    List<ProductDto> getAllProducts(String order);
    List<ProductDto> findByCategory(String category, String order);
    List<ProductDto> findByAlphabeticOrder(String order);
    Product findByMinPrice();
    Product findByMaxPrice();
    List<ProductDto> findByFreeShipping(String category, String order);
    List<ProductDto> findByPrestige(String prestige, String order);
    ProductDto checkStock(int id);
}
