package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.SalesRepo;
import com.proyectofinal.sistema_inventarios.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SalesImplementation implements SalesRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SalesImplementation(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int validarExistencias(int id) {
        String sql = "SELECT quantity FROM Products WHERE idProducts =" + id;

        ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {
            @Override
//this method extracts via spring the data from the table contact and set it in the extractor variable
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    double quantity = resultSet.getDouble("quantity");
                    if (quantity>0){
                        System.out.println("mas que 1");
                        return 1;
                    }
                }
                return null;
            }
        };

        jdbcTemplate.query(sql, extractor);
        return 0;

    }

    @Override
    public double devuelveTotal() {
        return 0;
    }
}
