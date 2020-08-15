package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.UserRepo;
import com.proyectofinal.sistema_inventarios.service.FormaPago;
import com.proyectofinal.sistema_inventarios.service.TipoPago;
import com.proyectofinal.sistema_inventarios.service.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOimplementation implements UserRepo{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDAOimplementation(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(Users users) {
        String sql = "Insert into Users (cedula,name,lastname, phone,tipopago,contrasena, email)"
                + " values (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,users.getCedula(), users.getName(), users.getLastName(), users.getPhone(), users.getTipoPago().toString(),users.getContrasena(), users.getEmail());

    }

    @Override
    public int update(Users users, int cedula) {
        String sql = "UPDATE Users SET cedula = ?, name = ?, lastname = ?, phone = ?, tipoPago = ?,contrasena = ?, email = ? WHERE cedula = ?";
        return jdbcTemplate.update(sql, users.getCedula(), users.getName(), users.getLastName(), users.getPhone(), users.getTipoPago().toString(),users.getContrasena(), users.getEmail(), cedula);

    }

    @Override
    public Users getUser(int id) {
        String sql = "SELECT * FROM Users WHERE cedula =" + id;

        ResultSetExtractor<Users> extractor = new ResultSetExtractor<Users>() {
            @Override
//this method extracts via spring the data from the table contact and set it in the extractor variable
            public Users extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    int cedula = resultSet.getInt("cedula");
                    String name = resultSet.getString("name");
                    String lastname = resultSet.getString("lastname");
                    String phone = resultSet.getString("phone");
                    String tipoPago = resultSet.getString("tipoPago");
                    String contrasena = resultSet.getString("contrasena");
                    String email = resultSet.getString("email");

                    return new Users(cedula, name, lastname, phone, FormaPago.valueOf(tipoPago),contrasena, email);
                }
                return null;
            }
        };

        return jdbcTemplate.query(sql, extractor);
    }

    @Override
    public int delete(int id) {

        String sql = "DELETE FROM Users WHERE cedula =" + id;
        return jdbcTemplate.update(sql);
        //File modified by SebasZ
    }

    @Override
    public List<Users> list() {
        String sql = "SELECT * FROM Users";
        RowMapper<Users> rowMapper = new RowMapper<Users>() {//Row Mapper class, it returns a List of the Class that we specified
            @Override
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {

                int cedula = resultSet.getInt("cedula");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String phone = resultSet.getString("phone");
                String tipoPago = resultSet.getString("tipoPago");
                String contrasena = resultSet.getString("contrasena");
                String email = resultSet.getString("email");

                return new Users(cedula, name, lastname, phone, FormaPago.valueOf(tipoPago),contrasena, email);
            }

        };
        return jdbcTemplate.query(sql, rowMapper);
    }

}
