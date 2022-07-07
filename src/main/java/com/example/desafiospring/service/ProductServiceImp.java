package com.example.desafiospring.service;

import com.example.desafiospring.dto.ProductDto;
import com.example.desafiospring.exception.NotFoundException;
import com.example.desafiospring.model.Product;
import com.example.desafiospring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements IProductService{

    @Autowired
    private ProductRepo repo;

    private List<Product> allProducts(String order) {
        switch (order){
            case "0":
                return repo.getAllProducts()
                        .stream()
                        .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                        .collect(Collectors.toList());
            case "1":
                return repo.getAllProducts()
                        .stream()
                        .sorted((p1, p2) -> p2.getName().compareTo(p1.getName()))
                        .collect(Collectors.toList());
            case "2":
                return repo.getAllProducts()
                        .stream()
                        .sorted((p1, p2) -> p1.compareTo(p2))
                        .collect(Collectors.toList());
            case "3":
                return repo.getAllProducts()
                        .stream()
                        .sorted((p1, p2) -> p2.compareTo(p1))
                        .collect(Collectors.toList());
            default:
                return repo.getAllProducts();
        }
    }

    @Override
    public List<ProductDto> createProducts(ArrayList<Product> products) {
        ArrayList<Product> listProducts = repo.saveProducts(products);

        List<ProductDto> listProductsDto = listProducts.stream()
                .map(ProductDto::new).collect(Collectors.toList());

        return listProductsDto;
    }

    @Override
    public List<ProductDto> getAllProducts(String order) {
        return allProducts(order).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByCategory(String category, String order) {
        List<ProductDto> allProductsDto = allProducts(order).stream()
                .filter(p -> p.getCategory().equals(category))
                .map(ProductDto::new).collect(Collectors.toList());

        if (allProductsDto.size() == 0) {
            throw new NotFoundException("Categoria não encontrada.");
        }

        return allProductsDto;
    }

    @Override
    public List<ProductDto> findByAlphabeticOrder(String order) {

        List<ProductDto> allProductsDto = allProducts(order)
                .stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .map(ProductDto::new)
                .collect(Collectors.toList());

        return allProductsDto;
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
    public List<ProductDto> findByFreeShipping(String category, String order) {
        List<ProductDto> allProductsDto = allProducts(order).stream()
                .filter(p -> p.getCategory().equals(category) && p.isFreeShipping())
                .map(ProductDto::new).collect(Collectors.toList());

        if (allProductsDto.size() == 0) {
            throw new NotFoundException("Não existe produto dessa categoria com frete grátis.");
        }

        return allProductsDto;
    }

    @Override
    public List<ProductDto> findByPrestige(String prestige, String order) {
        List<ProductDto> allProductsDto = allProducts(order).stream()
                .filter(p -> p.isFreeShipping() && p.getPrestige().equals(prestige))
                .map(ProductDto::new).collect(Collectors.toList());

        if (allProductsDto.size() == 0) {
            throw new NotFoundException("Não existe nenhum produto com frete grátis com essa avaliação.");
        }
        return allProductsDto;
    }

    @Override
    public ProductDto checkStock(int id){
        List<ProductDto> allProducts = getAllProducts(null);
        ProductDto productDto = allProducts.stream().filter(p -> p.getProductId() == id).findFirst().orElse(null);

        if (productDto == null){
            throw new NotFoundException("Produto não encontrado.");
        }
        return productDto;
    }
}
