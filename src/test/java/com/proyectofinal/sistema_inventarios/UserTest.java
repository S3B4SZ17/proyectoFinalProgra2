package com.proyectofinal.sistema_inventarios;

import com.proyectofinal.sistema_inventarios.Config.SpringJdbcConfig;
import com.proyectofinal.sistema_inventarios.DAO.UserDAOimplementation;
import com.proyectofinal.sistema_inventarios.repository.UserRepo;
import com.proyectofinal.sistema_inventarios.service.FormaPago;
import com.proyectofinal.sistema_inventarios.service.Users;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    @Autowired
    SpringJdbcConfig springJdbcConfig =new SpringJdbcConfig();
    UserRepo userRepo;

    @BeforeEach
    public void setConnectionDataBase(){

        userRepo = new UserDAOimplementation(springJdbcConfig.mysqlDataSource());
    }

    @Test
    public void testSaveUser() throws SQLException {
        try {
            Users users = new Users(1234567892,"Mark", "Brown","61025856", FormaPago.Credito,"Mark111", "mark@example.com");
            int result = userRepo.save(users);

            assertTrue(result >= 0);

        }catch (JDBCException exception){
            JOptionPane.showMessageDialog(null, exception);

        }


    }
    @Test
    public Users testGetUser(){
        Users users = userRepo.getUser(1234567891 );
        System.out.println(users.getCedula());
        if(users !=null)System.out.println(users.toString());
        return users;
    }

    @Test
    public void testGetAllUsers(){
        List<Users> users = userRepo.list();
        for(Users users1 : users){
            if(users !=null)System.out.println(users1.toString());

        }

    }

    @Test
    public void testDelete(){
        userRepo.delete(1234567891);
        List<Users> users = userRepo.list();
        for(Users users1 : users){
            if(users !=null)System.out.println(users1.toString());

        }

    }

    @Test
    public void testUpdateUser() throws SQLException {
        try {
            Users users = new Users(1234567893,"Sebas", "Zumbado","60024846", FormaPago.Contado,"Sebas111", "sebas@example.com");
            int result = userRepo.update(users, 1234567891);

            assertTrue(result >= 0);

        }catch (JDBCException exception){
            JOptionPane.showMessageDialog(null, exception);

        }


    }




}
