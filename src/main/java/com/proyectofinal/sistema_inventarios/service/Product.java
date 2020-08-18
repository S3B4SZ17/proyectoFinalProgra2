package com.proyectofinal.sistema_inventarios.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int idProducts;
    private String name;
    private double quantity;
    private double price;
    private String description;
    private LocalDateTime dateOfPurchase;



}
