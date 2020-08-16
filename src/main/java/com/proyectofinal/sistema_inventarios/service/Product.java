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
    private double price;
    private double quantity;
    private String description;
    private LocalDateTime dateOfPurchase;



}
