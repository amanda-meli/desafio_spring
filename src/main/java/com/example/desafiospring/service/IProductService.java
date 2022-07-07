package com.example.desafiospring.service;

import com.example.desafiospring.dto.ProductDto;
import com.example.desafiospring.model.Product;

import java.util.List;
import java.util.ArrayList;

public interface IProductService {
    List<ProductDto> createProducts(ArrayList<Product> products);
    List<ProductDto> getAllProducts();
    List<ProductDto> findByCategory(String category);
    List<ProductDto> findByAlphabeticOrder();
    Product findByMinPrice();
    Product findByMaxPrice();
    List<ProductDto> findByFreeShipping(String category);
    List<ProductDto> findByPrestige();
    ProductDto checkStock(int id);
}
