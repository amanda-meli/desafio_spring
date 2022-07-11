package com.example.desafiospring.model;

import lombok.Data;

/**
 * Classe contendo as informações dos produtos pra salvar/atualizar um carrinho de compra
 */
@Data
public class Purchase {
    private int productId;
    private int quantity;
}
