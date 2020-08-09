package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.ProductRepo;
import com.proyectofinal.sistema_inventarios.service.Product;

import java.util.List;

public class ProductDAOimplementation implements ProductRepo {
    @Override
    public int save(Product user) {
        return 0;
    }

    @Override
    public int update(Product user) {
        return 0;
    }

    @Override
    public Product getUser(int id) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<Product> list() {
        return null;
    }
}
