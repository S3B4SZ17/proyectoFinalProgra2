package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.ProductRepo;
import com.proyectofinal.sistema_inventarios.service.FormaPago;
import com.proyectofinal.sistema_inventarios.service.Product;
import com.proyectofinal.sistema_inventarios.service.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductDAOimplementation implements ProductRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductDAOimplementation(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(Product product) {
        int consecutivoProducto = getConsecutivoProducto()+1;
        String sql = "Insert into Products (idProducts,name,quantity,price, description,datePurchase)"
                + " values (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, consecutivoProducto, product.getName(), product.getQuantity(),product.getPrice(), product.getDescription(), product.getDateOfPurchase());

    }

    @Override
    public int update(Product product, int id) {
        String sql = "UPDATE Products SET idProducts = ?, name = ?, quantity = ?, price = ?,description = ?, datePurchase = ? WHERE idProducts = ?";
        return jdbcTemplate.update(sql, product.getIdProducts(), product.getName(), product.getQuantity(), product.getPrice(), product.getDescription(), product.getDateOfPurchase(), id);

    }

    @Override
    public Product getProduct(int id, String nombreproducto) {
        String sql = "SELECT * FROM Products WHERE idProducts =" + id + " or name = " + "'"+nombreproducto+"'";

        ResultSetExtractor<Product> extractor = new ResultSetExtractor<Product>() {
            @Override
//this method extracts via spring the data from the table contact and set it in the extractor variable
            public Product extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    int idProducts = resultSet.getInt("idProducts");
                    String name = resultSet.getString("name");
                    double quantity = resultSet.getDouble("quantity");
                    double price = resultSet.getDouble("price");
                    String description = resultSet.getString("description");
                    LocalDateTime datePurchase = resultSet.getTimestamp("datePurchase").toLocalDateTime();

                    return new Product(idProducts, name, quantity,price, description, datePurchase);
                }
                return null;
            }
        };

        return jdbcTemplate.query(sql, extractor);
    }

    @Override
    public int delete(int id) {

        String sql = "DELETE FROM Products WHERE idProducts =" + id;
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Product> list() {
        String sql = "SELECT * FROM Products";
        RowMapper<Product> rowMapper = new RowMapper<Product>() {//Row Mapper class, it returns a List of the Class that we specified
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {

                int idProducts = resultSet.getInt("idProducts");
                String name = resultSet.getString("name");
                double quantity = resultSet.getDouble("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                LocalDateTime datePurchase = resultSet.getTimestamp("datePurchase").toLocalDateTime();

                return new Product(idProducts, name, quantity,price, description, datePurchase);
            }

        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int getConsecutivoProducto(){
        String sql = "SELECT MAX(idProducts) as idProducto FROM Products";
        AtomicInteger consecutivo = new AtomicInteger();
        ResultSetExtractor<Integer> extractor = (ResultSet resultSet) ->  {
            if (resultSet.next()) {
                int numeroFactura = resultSet.getInt("idProducto");
                consecutivo.set(numeroFactura);
            }
            return null;
        };
        jdbcTemplate.query(sql, extractor);
        return consecutivo.intValue();

    }
}
