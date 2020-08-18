package com.proyectofinal.sistema_inventarios.Main;

import com.proyectofinal.sistema_inventarios.Config.SpringJdbcConfig;
import com.proyectofinal.sistema_inventarios.DAO.InvoiceImplementation;
import com.proyectofinal.sistema_inventarios.DAO.ProductDAOimplementation;
import com.proyectofinal.sistema_inventarios.DAO.UserDAOimplementation;
import com.proyectofinal.sistema_inventarios.repository.InvoiceRepo;
import com.proyectofinal.sistema_inventarios.repository.ProductRepo;
import com.proyectofinal.sistema_inventarios.repository.UserRepo;
import com.proyectofinal.sistema_inventarios.service.Invoices;
import com.proyectofinal.sistema_inventarios.service.Product;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;

public class MainTest {



    public static void main(String[] args) {
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.now();
        SpringJdbcConfig springJdbcConfig = new SpringJdbcConfig();
        UserRepo userRepo = new UserDAOimplementation(springJdbcConfig.postgresqlDataSource());
        InvoiceRepo invoiceRepo = new InvoiceImplementation(springJdbcConfig.postgresqlDataSource());
        ProductRepo productRepo = new ProductDAOimplementation(springJdbcConfig.postgresqlDataSource());


        Invoices invoices = new Invoices();
        invoices.setUsers(userRepo.getUser(1234567892));
        invoices.setInvoiceDate(LocalDateTime.now());
        //invoices.setProducts(new Product[]{productRepo.getProduct(1001)});
        invoices.setTotal(1500);
        invoiceRepo.subirFactura(invoices);

        invoiceRepo.subirDetalleFactura(new LinkedList<Product>(productRepo.list()));

    }
}
