package com.proyectofinal.sistema_inventarios.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoices {
    private int idFactura;
    private Users users;
    private Product[] products;
    private LocalDateTime invoiceDate;
    private double total;


}
