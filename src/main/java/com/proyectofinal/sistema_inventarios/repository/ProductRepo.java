package com.proyectofinal.sistema_inventarios.repository;

import com.proyectofinal.sistema_inventarios.service.Product;


import java.util.List;

public interface ProductRepo {

    public int  save(Product user);
    public int  update(Product user, int id);
    public Product getProduct(int id, String nombreproducto);
    public int  delete(int id);
    public List<Product> list();
}
