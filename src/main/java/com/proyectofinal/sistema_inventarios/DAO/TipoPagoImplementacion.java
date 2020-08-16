package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.TipoPagoRepo;
import com.proyectofinal.sistema_inventarios.service.TipoPago;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class TipoPagoImplementacion implements TipoPagoRepo {
    private JdbcTemplate jdbcTemplate;


    public TipoPagoImplementacion(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int validarTarjeta(int numeroTarjeta, Date fecha, int cvv) {
        String sql = "SELECT numeroTarjeta,fechaExpiracion,CVV FROM Tarjetas WHERE numeroTarjeta =" + numeroTarjeta;
        AtomicInteger result = new AtomicInteger();
        ResultSetExtractor<Integer> extractor = (ResultSet resultSet) ->  {
            if (resultSet.next()) {
                int Tarjeta = resultSet.getInt("numeroTarjeta");
                Date fechaExpiracion = resultSet.getDate("fechaExpiracion");
                int CVV = resultSet.getInt("CVV");

                if (Tarjeta == numeroTarjeta && fecha == fechaExpiracion && cvv == CVV){
                    JOptionPane.showMessageDialog(null, "Validado!!");
                    result.set(1);

                }else{
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
        String sql = "Insert into Tarjetas (numeroTarjeta,tipo,fechaExpiracion, CVV)"
                + " values (?,?,?,?)";
        return jdbcTemplate.update(sql,tipoPago.getNumeroTarjeta(),tipoPago.getTipo().toString(),tipoPago.getFechaExpiracion(),tipoPago.getCVV());

    }
}
