package com.proyectofinal.sistema_inventarios.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoices {
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Users> users = new ArrayList<Users>();
    private Date invoiceDate;
    private double total;


}
