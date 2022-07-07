package com.example.desafiospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @autor CodeFellas555
 *
 *Responsável por mapear a entidade de produtos que vai ser tratado na regra de negocio.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int productId;
    private String name;
    private String category;
    private String brand;
    private double price;
    private int quantity;
    private boolean freeShipping;
    private String prestige;
}
