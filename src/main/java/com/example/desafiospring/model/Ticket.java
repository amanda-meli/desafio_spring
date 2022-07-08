package com.example.desafiospring.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private int id;
    private List<Product> articles;
    private double total;
}
