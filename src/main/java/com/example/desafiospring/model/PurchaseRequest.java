package com.example.desafiospring.model;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseRequest {
    private int ticketId;
    private List<Purchase> articlesPurchaseRequest;
}
