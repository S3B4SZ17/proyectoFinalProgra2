package com.proyectofinal.sistema_inventarios.repository;

import com.proyectofinal.sistema_inventarios.service.Invoices;
import com.proyectofinal.sistema_inventarios.service.Product;
import com.proyectofinal.sistema_inventarios.service.Users;

public interface InvoiceRepo {
    public Product getProducts(int id);
    public Users getUser(int cedula);
    public void total();
    public Invoices subirFactura();
    public void enviarCorreo();



}
