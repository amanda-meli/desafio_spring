package com.example.desafiospring.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Classe contendo as informações de produtos
 */
public class Product implements Comparable<Product>{
    private int productId;
    private String name;
    private String category;
    private String brand;
    private double price;
    private int quantity;
    private boolean freeShipping;
    private String prestige;


    @Override
    public int compareTo(Product o) {
        return (int) (this.getPrice() - o.getPrice());
    }
}
