package com.proyectofinal.sistema_inventarios.repository;

import com.proyectofinal.sistema_inventarios.service.Product;


import java.util.List;

public interface ProductRepo {

    public void save(Product user);
    public void update(Product user);
    public Product getUser(int id);
    public void delete(int id);
    public List<Product> list();
}
