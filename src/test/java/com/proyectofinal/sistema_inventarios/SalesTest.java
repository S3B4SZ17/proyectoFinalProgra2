package com.proyectofinal.sistema_inventarios;

import com.proyectofinal.sistema_inventarios.Config.SpringJdbcConfig;
import com.proyectofinal.sistema_inventarios.DAO.ProductDAOimplementation;
import com.proyectofinal.sistema_inventarios.DAO.SalesImplementation;
import com.proyectofinal.sistema_inventarios.repository.ProductRepo;
import com.proyectofinal.sistema_inventarios.repository.SalesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SalesTest {
    @Autowired
    SpringJdbcConfig springJdbcConfig =new SpringJdbcConfig();
    SalesRepo salesRepo;

    @BeforeEach
    public void setConnectionDataBase(){
        salesRepo = new SalesImplementation(springJdbcConfig.mysqlDataSource());
    }

    @Test
    public void testValidation(){
        int res = salesRepo.validarExistencias(1002);
        System.out.println(res);
    }
}
