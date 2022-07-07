package com.example.desafiospring.controller;

import com.example.desafiospring.dto.ProductDto;
import com.example.desafiospring.model.Product;
import com.example.desafiospring.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/insert-articles-request")
public class ProductController {

    @Autowired
    private IProductService service;

    @PostMapping
    public ResponseEntity<List<ProductDto>> createProducts(@RequestBody ArrayList<Product> products ){
        List<ProductDto> dto = service.createProducts(products);
        return ResponseEntity.ok().body(dto);
    }

//    getAllProducts();
//    getByCategory();
//    getByAlphabeticOrder();
//    getByMinPrice();
//    getByMaxPrice();
//    getByFreeShipping();
//    getByPrestige();
//    saveProducts();
}
