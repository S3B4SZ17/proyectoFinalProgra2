package com.proyectofinal.sistema_inventarios.repository;

import com.proyectofinal.sistema_inventarios.service.Users;

import java.sql.SQLException;
import java.util.List;

public interface UserRepo {

    public int save(Users users) throws SQLException;

    public int update(Users users, int cedula);

    public Users getUser(int id);

    public int delete(int id);

    public List<Users> list();

}
