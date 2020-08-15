package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.InvoiceRepo;
import com.proyectofinal.sistema_inventarios.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class InvoiceImplementation implements InvoiceRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public InvoiceImplementation(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SubjectEmail = "Confirmacion de Factura numero ";
    private final String BodyEmail = "Gracias por realizar su compra con Quantum Electronics\n"+
                                        "Adjunto encontrara el archivo en fromato  PDF";
    private MailService mailService;
    private MailParts mailParts;

    @Override
    public void enviarCorreo() {
        Invoices invoices = subirFactura();
        mailService.sendMail(new MailParts(SubjectEmail+invoices.getIdFactura(),invoices.getUsers().getEmail(),BodyEmail));
    }

    @Override
    public Product getProducts(int id) {
        return null;
    }

    @Override
    public Invoices subirFactura() {
        return null;
    }

    @Override
    public Users getUser(int cedula) {
        return null;
    }

    @Override
    public void total() {

    }
}
