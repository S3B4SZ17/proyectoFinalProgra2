package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.ProductRepo;
import com.proyectofinal.sistema_inventarios.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ProductDAOimplementation implements ProductRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductDAOimplementation(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(Product product) {
        String sql = "Insert into Products (id,name,quantity, description,datePurchase)"
                + " values (?,?,?,?,?)";
        return jdbcTemplate.update(sql, product.getId(), product.getName(), product.getQuantity(), product.getDescription(), product.getDateOfPurchase());

    }

    @Override
    public int update(Product product) {
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
