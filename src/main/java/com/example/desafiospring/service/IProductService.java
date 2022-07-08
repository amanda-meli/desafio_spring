package com.example.desafiospring.service;

import com.example.desafiospring.dto.ProductDto;
import com.example.desafiospring.model.Product;
import com.example.desafiospring.model.Purchase;
import com.example.desafiospring.model.PurchaseRequest;
import com.example.desafiospring.model.Ticket;

import java.util.List;
import java.util.ArrayList;

public interface IProductService {
    List<ProductDto> createProducts(ArrayList<Product> products);
    List<ProductDto> getAllProducts(String order);
    List<ProductDto> findByCategory(String category, String order);
    List<ProductDto> findByFreeShipping(String category, String order);
    List<ProductDto> findByPrestige(String prestige, String order);
    ProductDto checkStock(int id);
    Ticket purchaseRequest(List<Purchase> purchases);
    Ticket shoppingCar(PurchaseRequest purchases);
}
