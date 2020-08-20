package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.Config.SpringJdbcConfig;
import com.proyectofinal.sistema_inventarios.repository.TipoPagoRepo;
import com.proyectofinal.sistema_inventarios.repository.UserRepo;
import com.proyectofinal.sistema_inventarios.service.FormaPago;
import com.proyectofinal.sistema_inventarios.service.Product;
import com.proyectofinal.sistema_inventarios.service.TipoPago;
import com.proyectofinal.sistema_inventarios.service.Users;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.dao.DataAccessException;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class TipoPagoImplementacion implements TipoPagoRepo {
    private JdbcTemplate jdbcTemplate;
    private static SpringJdbcConfig springJdbcConfig =new SpringJdbcConfig();
    private static UserRepo userRepo = new UserDAOimplementation(springJdbcConfig.postgresqlDataSource());


    public TipoPagoImplementacion(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int validarTarjeta(int numeroTarjeta, String fecha, String cvv) {
        String sql = "SELECT numeroTarjeta,fechaExpiracion,CVV FROM Tarjetas WHERE numeroTarjeta =" + numeroTarjeta;
        AtomicInteger result = new AtomicInteger();
        ResultSetExtractor<Integer> extractor = (ResultSet resultSet) ->  {
            if (resultSet.next()) {
                int Tarjeta = resultSet.getInt("numeroTarjeta");
                String fechaExpiracion = resultSet.getString("fechaExpiracion");
                String CVV = resultSet.getString("CVV");

                if (Tarjeta == numeroTarjeta && fechaExpiracion.equals(fecha) && CVV.equals(cvv) ){
                    System.out.println(Tarjeta + fechaExpiracion+CVV);
                    JOptionPane.showMessageDialog(null, "Validado!!");
                    result.set(1);

                }else{
                    System.out.println(Tarjeta + fechaExpiracion+ CVV);
                    JOptionPane.showMessageDialog(null, "La informacion de la tarjeta es incorrecta");
                    result.set(0);
                }
            }
            return null;
        };

        jdbcTemplate.query(sql, extractor);
        return result.intValue();
    }

    @Override
    public int ingresarInfoTarjeta(TipoPago tipoPago) {
        if(validarTarjeta(tipoPago.getNumeroTarjeta(),tipoPago.getFechaExpiracion(),tipoPago.getCVV())==1){
            String sql = "Insert into Tarjetas (numeroTarjeta,tipo,fechaExpiracion, CVV,cedulaUsuario)"
                    + " values (?,?,?,?,?)";
            return jdbcTemplate.update(sql,tipoPago.getNumeroTarjeta(),tipoPago.getTipo().toString(),tipoPago.getFechaExpiracion(),tipoPago.getCVV(),tipoPago.getUser().getCedula());

        }
       return 0;
    }

    @Override
    public TipoPago getTarjeta(int numero) {
        String sql = "SELECT * FROM Tarjetas WHERE numeroTarjeta = " +numero;

        ResultSetExtractor<TipoPago> extractor = new ResultSetExtractor<TipoPago>() {
            @Override
//this method extracts via spring the data from the table contact and set it in the extractor variable
            public TipoPago extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    String tipo = resultSet.getString("tipo");
                    int numeroTarjeta = resultSet.getInt("numeroTarjeta");
                    String cvv = resultSet.getString("CVV");
                    int usuario = resultSet.getInt("cedulaUsuario");
                    String fechaEx = resultSet.getString("fechaExpiracion");

                    return new TipoPago(FormaPago.valueOf(tipo), numeroTarjeta, fechaEx,cvv, userRepo.getUser(usuario));
                }
                return null;
            }
        };

        return jdbcTemplate.query(sql, extractor);
    
    
    }
    
    
}
