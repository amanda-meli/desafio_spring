package com.example.desafiospring.controller;

import com.example.desafiospring.dto.ProductDto;
import com.example.desafiospring.model.Product;
import com.example.desafiospring.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    private IProductService service;

    @PostMapping("insert-articles-request")
    public ResponseEntity<List<ProductDto>> createProducts(@RequestBody ArrayList<Product> products ){
        List<ProductDto> dto = service.createProducts(products);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("articles")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam @Nullable String category){
        List<ProductDto> dto;

        if (category != null) {
            dto = service.findByCategory(category);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }

        dto = service.getAllProducts();
        return ResponseEntity.ok().body(dto);
    }
//    getByCategory();
//    getByAlphabeticOrder();
//    getByMinPrice();
//    getByMaxPrice();
//    getByFreeShipping();
//    getByPrestige();
//    saveProducts();

    @GetMapping("articles/{id}")
    public ResponseEntity<Integer> checkStock(@PathVariable int id){
        ProductDto dto = service.checkStock(id);
        return new ResponseEntity<>(dto.getQuantity(), HttpStatus.OK);
    }
}
