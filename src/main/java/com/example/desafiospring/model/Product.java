package com.example.desafiospring.model;

import lombok.*;

/**
 * @autor CodeFellas555
 *
 *Responsável por mapear a entidade de produtos que vai ser tratado na regra de negocio.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
