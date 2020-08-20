package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.Config.SpringJdbcConfig;
import com.proyectofinal.sistema_inventarios.repository.InvoiceRepo;
import com.proyectofinal.sistema_inventarios.repository.ProductRepo;
import com.proyectofinal.sistema_inventarios.repository.UserRepo;
import com.proyectofinal.sistema_inventarios.service.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class InvoiceImplementation implements InvoiceRepo {

    private JdbcTemplate jdbcTemplate;
    private SpringJdbcConfig springJdbcConfig =new SpringJdbcConfig();
    private UserRepo userRepo = new UserDAOimplementation(springJdbcConfig.postgresqlDataSource());
    private ProductRepo productRepo = new ProductDAOimplementation(springJdbcConfig.postgresqlDataSource());
    List<Product> listaProductos = new LinkedList<>();

    public InvoiceImplementation(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SubjectEmail = "Confirmacion de Factura numero ";
    private final String BodyEmail = "Gracias por realizar su compra con Quantum Electronics\n"+
                                     "Adjunto encontrara el archivo en fromato  TXT";
    private MailService mailService = new MailService();

    @Override
    public void enviarCorreo(Invoices invoices, String fileName) {
        mailService.sendEmail(new MailParts(SubjectEmail+invoices.getIdFactura(),invoices.getUsers().getEmail(),BodyEmail,fileName));
    }

    @Override
    public int subirFactura(Invoices invoices) {
        int consecutivoFactura = getConsecutivoFactura() + 1 ;
        String sql = "Insert into Facturas (idFactura,cedulaUsuario,invoiceDate,total)"
                + " values (?,?,?,?)";
        return jdbcTemplate.update(sql,consecutivoFactura,invoices.getUsers().getCedula(),invoices.getInvoiceDate(),invoices.getTotal());
    }

    @Override
    public void subirDetalleFactura(LinkedList<Product> products) {
        int consecutivoFactura = getConsecutivoFactura();
        String sql = "Insert into DetalleProductos (idFactura,idProducto,totalLinea)"
                + " values (?,?,?)";

        for(Product products1 : products){
            jdbcTemplate.update(sql,consecutivoFactura,products1.getIdProducts(),products1.getPrice()*products1.getQuantity());
        }

        String sql2 = "UPDATE Facturas SET total = ? WHERE idFactura = ?";
        jdbcTemplate.update(sql2,getTotal(consecutivoFactura),consecutivoFactura);

    }

    @Override
    public List<Invoices> getFactura(int id) {
        
        String sql = "Select f.idFactura, f.cedulaUsuario, f.invoiceDate, P.name, SUM(DP.totalLinea) as TotalFinal from Facturas f\n" +
                    "    inner join DetalleProductos DP on f.idFactura = DP.idFactura INNER JOIN Products P on DP.idProducto = P.idProducts\n" +
                    "GROUP BY f.idFactura, f.cedulaUsuario, f.invoiceDate, P.name\n" +
                    "HAVING f.idFactura =" + id;

         RowMapper<Invoices> rowMapper = new RowMapper<Invoices>() {
            @Override
//this method extracts via spring the data from the table contact and set it in the extractor variable
            public Invoices mapRow(ResultSet resultSet, int i) throws SQLException, DataAccessException {
                    
                    int idProducts = resultSet.getInt("idFactura");
                    int cedula = resultSet.getInt("cedulausuario");
                    String nombre = resultSet.getString("name");
                    Double total = resultSet.getDouble("TotalFinal");
                    LocalDateTime fechafactura = resultSet.getTimestamp("invoicedate").toLocalDateTime();
                    listaProductos.add(productRepo.getProduct(0,nombre));
                    return new Invoices(idProducts, userRepo.getUser(cedula), listaProductos,fechafactura,total );
                
                
            }
        };

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Invoices> list() {
        String sql = "SELECT * FROM Facturas";
        RowMapper<Invoices> rowMapper = new RowMapper<Invoices>() {//Row Mapper class, it returns a List of the Class that we specified
            @Override
            public Invoices mapRow(ResultSet resultSet, int i) throws SQLException {

                int idProducts = resultSet.getInt("idFactura");
                int cedula = resultSet.getInt("cedulausuario");
                double total = resultSet.getDouble("total");
                LocalDateTime datePurchase = resultSet.getTimestamp("invoicedate").toLocalDateTime();

                return new Invoices(idProducts, userRepo.getUser(cedula), datePurchase, total);
            }

        };
        return jdbcTemplate.query(sql, rowMapper);
        
    
    }

    

    @Override
    public double getTotal(int id) {
        String sql = "SELECT SUM(totallinea) as totallinea FROM DetalleProductos WHERE idFactura="+ id;
        AtomicReference<Double> totalfactura = new AtomicReference<Double>();
        ResultSetExtractor<Double> extractor = (ResultSet resultSet) ->  {
            if (resultSet.next()) {
                double total = resultSet.getDouble("totallinea");
                totalfactura.set(total);
            }
            return null;
        };
        jdbcTemplate.query(sql, extractor);
        return totalfactura.get();


    }

    public int getConsecutivoFactura(){
        String sql = "SELECT MAX(idFactura) as idFactura FROM Facturas";
        AtomicInteger consecutivo = new AtomicInteger();
        ResultSetExtractor<Integer> extractor = (ResultSet resultSet) ->  {
            if (resultSet.next()) {
                int numeroFactura = resultSet.getInt("idFactura");
                consecutivo.set(numeroFactura);
            }
            return null;
        };
        jdbcTemplate.query(sql, extractor);
        return consecutivo.intValue();

    }
}
