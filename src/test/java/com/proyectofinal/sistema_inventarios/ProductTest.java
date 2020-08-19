package com.proyectofinal.sistema_inventarios;

import com.proyectofinal.sistema_inventarios.Config.SpringJdbcConfig;
import com.proyectofinal.sistema_inventarios.DAO.ProductDAOimplementation;

import com.proyectofinal.sistema_inventarios.repository.ProductRepo;
import com.proyectofinal.sistema_inventarios.service.Product;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest {
    LocalDateTime date = LocalDateTime.now();
    @Autowired
    SpringJdbcConfig springJdbcConfig =new SpringJdbcConfig();
    ProductRepo productRepo;

    @BeforeEach
    public void setConnectionDataBase(){
        productRepo = new ProductDAOimplementation(springJdbcConfig.postgresqlDataSource());
    }

    @Test
    public void testSaveProduct() throws SQLException {
        try {
            Product product = new Product(1002,"Cereal", 150,300,"Cereal dulce", LocalDateTime.now());
            int result = productRepo.save(product);

            assertTrue(result >= 0);

        }catch (JDBCException exception){
            JOptionPane.showMessageDialog(null, exception);

        }


    }
    @Test
    public void testGetProduct(){
        Product product = productRepo.getProduct(1002,"Arroz" );
        if(product !=null)System.out.println(product.toString());
    }

    @Test
    public void testGetAllProducts(){
        List<Product> users = productRepo.list();
        for(Product users1 : users){
            if(users !=null)System.out.println(users1.toString());

        }

    }

    @Test
    public void testDelete(){
        productRepo.delete(1002);
        List<Product> product = productRepo.list();
        for(Product product1 : product){
            if(product !=null)System.out.println(product1.toString());

        }

    }

    @Test
    public void testUpdateProduct() throws SQLException {
        try {
            Product product = new Product(1002,"Frijoles", 50,1100,"Frijoles Rojos", LocalDateTime.now());
            int result = productRepo.update(product, 1002);

            assertTrue(result >= 0);

        }catch (JDBCException exception){
            JOptionPane.showMessageDialog(null, exception);

        }


    }
}
