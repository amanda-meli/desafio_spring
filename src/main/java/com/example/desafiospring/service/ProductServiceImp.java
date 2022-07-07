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

    private List<Product> allProducts() {
        return repo.getAllProducts();
    }

    @Override
    public List<ProductDto> createProducts(ArrayList<Product> products) {
        ArrayList<Product> listProducts = repo.saveProducts(products);

        List<ProductDto> listProductsDto = listProducts.stream()
                .map(ProductDto::new).collect(Collectors.toList());

        return listProductsDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return allProducts().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByCategory(String category) {
        List<ProductDto> allProductsDto = allProducts().stream()
                .filter(p -> p.getCategory().equals(category))
                .map(ProductDto::new).collect(Collectors.toList());

        if (allProductsDto.size() == 0) {
            throw new NotFoundException("Categoria não encontrada.");
        }

        return allProductsDto;
    }

    @Override
    public List<ProductDto> findByAlphabeticOrder() {
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
    public List<ProductDto> findByFreeShipping(String category) {
        List<ProductDto> allProductsDto = allProducts().stream()
                .filter(p -> p.getCategory().equals(category) && p.isFreeShipping())
                .map(ProductDto::new).collect(Collectors.toList());

        if (allProductsDto.size() == 0) {
            throw new NotFoundException("Não existe produto dessa categoria com frete grátis.");
        }

        return allProductsDto;
    }

    @Override
    public List<ProductDto> findByPrestige() {
        return null;
    }

    @Override
    public ProductDto checkStock(int id){
        List<ProductDto> allProducts = getAllProducts();
        ProductDto productDto = allProducts.stream().filter(p -> p.getProductId() == id).findFirst().orElse(null);

        if (productDto == null){
            throw new NotFoundException("Produto não encontrado.");
        }
        return productDto;
    }
}
