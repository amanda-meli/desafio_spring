package com.example.desafiospring.dto;

import com.example.desafiospring.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Informações básicas de um produto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int productId;
    private String name;
    private int quantity;

    public ProductDto(Product p) {
        this.productId = p.getProductId();
        this.name = p.getName();
        this.quantity = p.getQuantity();
    }
}
