package com.example.desafiospring.controller;

import com.example.desafiospring.model.Product;
import com.example.desafiospring.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @PostMapping
    public ResponseEntity createProducts(@RequestBody ArrayList<Product> products ){
        service.createProducts(products);
        return new ResponseEntity(HttpStatus.CREATED);
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
