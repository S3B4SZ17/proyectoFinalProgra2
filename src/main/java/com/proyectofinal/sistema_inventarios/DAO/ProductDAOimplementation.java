package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.ProductRepo;
import com.proyectofinal.sistema_inventarios.service.FormaPago;
import com.proyectofinal.sistema_inventarios.service.Product;
import com.proyectofinal.sistema_inventarios.service.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
    public int update(Product product, int id) {
        String sql = "UPDATE Products SET id = ?, name = ?, quantity = ?, phone = ?, description = ?, datePurchase = ? WHERE id = ?";
        return jdbcTemplate.update(sql, product.getId(), product.getName(), product.getQuantity(), product.getDescription(), product.getDateOfPurchase(), id);

    }

    @Override
    public Product getUser(int id) {
        String sql = "SELECT * FROM Products WHERE id =" + id;

        ResultSetExtractor<Product> extractor = new ResultSetExtractor<Product>() {
            @Override
//this method extracts via spring the data from the table contact and set it in the extractor variable
            public Product extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double quantity = resultSet.getDouble("quantity");
                    String description = resultSet.getString("description");
                    Date datePurchase = resultSet.getDate("datePurchase");

                    return new Product(id, name, quantity, description, datePurchase);
                }
                return null;
            }
        };

        return jdbcTemplate.query(sql, extractor);
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
