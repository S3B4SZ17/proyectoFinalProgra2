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
    private int idFactura;
    private Users users = new Users();
    private Product products = new Product();
    private Date invoiceDate;
    private double total;


}
