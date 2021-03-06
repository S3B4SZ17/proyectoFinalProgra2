package com.proyectofinal.sistema_inventarios.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private double quantity;
    private String description;
    private Date dateOfPurchase;



}
