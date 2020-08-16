package com.proyectofinal.sistema_inventarios.repository;

import com.proyectofinal.sistema_inventarios.service.Invoices;
import com.proyectofinal.sistema_inventarios.service.Product;
import com.proyectofinal.sistema_inventarios.service.Users;

import java.util.LinkedList;

public interface InvoiceRepo {
    public Product getProducts(int id);
    public Users getUser(int cedula);
    public double getTotal(int id);
    public int subirFactura(Invoices invoices);
    public void subirDetalleFactura(LinkedList<Product> products);
    public void enviarCorreo(Invoices invoices);
    public Invoices getFactura(int id);



}
