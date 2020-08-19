package com.proyectofinal.sistema_inventarios.repository;

import com.proyectofinal.sistema_inventarios.service.Invoices;
import com.proyectofinal.sistema_inventarios.service.Product;
import com.proyectofinal.sistema_inventarios.service.Users;

import java.util.LinkedList;
import java.util.List;

public interface InvoiceRepo {
    public List<Invoices> list();
    public double getTotal(int id);
    public int subirFactura(Invoices invoices);
    public void subirDetalleFactura(LinkedList<Product> products);
    public void enviarCorreo(Invoices invoices);
    public List<Invoices> getFactura(int id);



}
