package com.example.desafiospring.model;

import lombok.Data;

import java.util.List;

/**
 * Classe contendo as informações para salvar/atualizar um carrinho de compra
 */
@Data
public class PurchaseRequest {
    private int ticketId;
    private List<Purchase> articlesPurchaseRequest;
}
