package com.example.desafiospring.controller;

import com.example.desafiospring.dto.ProductDto;
import com.example.desafiospring.exception.MethodNotAllowedException;
import com.example.desafiospring.model.Product;
import com.example.desafiospring.model.Purchase;
import com.example.desafiospring.model.PurchaseRequest;
import com.example.desafiospring.model.Ticket;
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
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam @Nullable String order,
            @RequestParam @Nullable String category,
            @RequestParam @Nullable boolean isFreeShipping,
            @RequestParam @Nullable String prestige

    ){

        if(order == null){
            order = "-1";
        }

        List<ProductDto> dto;

        if (category != null) {
            if (isFreeShipping) {
                dto = service.findByFreeShipping(category, order);
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }

            dto = service.findByCategory(category, order);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        if (prestige != null) {
            if (isFreeShipping) {
                dto = service.findByPrestige(prestige, order);
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }

            throw new MethodNotAllowedException("Não existe filtro por avaliação.");
        }

        dto = service.getAllProducts(order);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("articles/{id}")
    public ResponseEntity<Integer> checkStock(@PathVariable int id){
        ProductDto dto = service.checkStock(id);
        return new ResponseEntity<>(dto.getQuantity(), HttpStatus.OK);
    }

    @PostMapping("purchase-request")
    public ResponseEntity<Ticket> purchaseRequest(@RequestBody PurchaseRequest purchase){
        Ticket ticket = service.purchaseRequest(purchase.getArticlesPurchaseRequest());
        return ResponseEntity.ok().body(ticket);
    }

    //@PostMapping("shopping-car")
    //public ResponseEntity<Ticket> shoppingCar(@RequestBody PurchaseRequest purchase){
        //Ticket ticket = service.purchaseRequest(purchase.getArticlesPurchaseRequest());
        //return ResponseEntity.ok().body(ticket);
    //}
}
