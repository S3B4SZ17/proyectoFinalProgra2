package com.proyectofinal.sistema_inventarios;

import com.proyectofinal.sistema_inventarios.Config.SpringJdbcConfig;
import com.proyectofinal.sistema_inventarios.DAO.InvoiceImplementation;
import com.proyectofinal.sistema_inventarios.repository.InvoiceRepo;
import com.proyectofinal.sistema_inventarios.service.Invoices;
import com.proyectofinal.sistema_inventarios.service.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TestInvoice {
    LocalDateTime date = LocalDateTime.now();
    @Autowired
    SpringJdbcConfig springJdbcConfig =new SpringJdbcConfig();
    InvoiceRepo invoiceRepo;

    @BeforeEach
    public void setConnectionDataBase(){
        invoiceRepo = new InvoiceImplementation(springJdbcConfig.postgresqlDataSource());
    }

    @Test
    public void testSubirFactura(){
        Invoices invoices = new Invoices();
        //invoices.setUsers(new UserTest().testGetUser());
        invoices.setInvoiceDate(date);
        invoices.setProducts(new Product[]{new Product(1005, "leche", 600, 15, "Descremada", LocalDateTime.now())});
        invoiceRepo.subirFactura(invoices);

    }
}
