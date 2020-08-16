package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.InvoiceRepo;
import com.proyectofinal.sistema_inventarios.service.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class InvoiceImplementation implements InvoiceRepo {

    private JdbcTemplate jdbcTemplate;

    public InvoiceImplementation(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SubjectEmail = "Confirmacion de Factura numero ";
    private final String BodyEmail = "Gracias por realizar su compra con Quantum Electronics\n"+
                                        "Adjunto encontrara el archivo en fromato  PDF";
    private MailService mailService;

    @Override
    public void enviarCorreo(Invoices invoices) {
        mailService.sendEmail(new MailParts(SubjectEmail+invoices.getIdFactura(),invoices.getUsers().getEmail(),BodyEmail));
    }

    @Override
    public Product getProducts(int id) {
        return null;
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
    public Invoices getFactura(int id) {
        return null;
    }

    @Override
    public Users getUser(int cedula) {
        return null;
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
