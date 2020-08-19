package com.proyectofinal.sistema_inventarios.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoices {
    private int idFactura;
    private Users users;
    private List<Product> products;
    private LocalDateTime invoiceDate;
    private double total;

    public Invoices(int idFactura, Users users, LocalDateTime invoiceDate, double total) {
        this.idFactura = idFactura;
        this.users = users;
        this.invoiceDate = invoiceDate;
        this.total = total;
    }
   
}
