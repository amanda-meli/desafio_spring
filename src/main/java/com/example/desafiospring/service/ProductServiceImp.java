package com.example.desafiospring.service;

import com.example.desafiospring.model.Product;
import com.example.desafiospring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductServiceImp implements IProductService{

    @Autowired
    private ProductRepo repo;

    @Override
    public void createProducts(ArrayList<Product> products) {
        repo.saveProducts(products);
    }

    @Override
    public ArrayList<Product> findByCategory(String category) {
        return null;
    }

    @Override
    public ArrayList<Product> findByAlphabeticOrder() {
        return null;
    }

    @Override
    public Product findByMinPrice() {
        return null;
    }

    @Override
    public Product findByMaxPrice() {
        return null;
    }

    @Override
    public ArrayList<Product> findByFreeShipping() {
        return null;
    }

    @Override
    public ArrayList<Product> findByPrestige() {
        return null;
    }
}
