package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.SalesRepo;
import com.proyectofinal.sistema_inventarios.service.MailParts;
import com.proyectofinal.sistema_inventarios.service.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class SalesImplementation implements SalesRepo {

    private MailService mailService = new MailService();
    private JdbcTemplate jdbcTemplate;


    public SalesImplementation(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public double validarExistencias(int id) {
        String sql = "SELECT quantity,name FROM Products WHERE idProducts =" + id;
        AtomicReference<Double> result = new AtomicReference<Double>();
        ResultSetExtractor<Integer> extractor = (ResultSet resultSet) ->  {
                if (resultSet.next()) {
                    double quantity = resultSet.getDouble("quantity");
                    String product = resultSet.getString("name");
                    if (quantity>=20){
                        result.set(quantity);
                    }else if(quantity<20 && quantity>0){
                        result.set(1.0);
                        enviarAlertaMinimodeStock(product,quantity);
                    }else{

                    }
                }
                return null;
        };

        jdbcTemplate.query(sql, extractor);
        return result.get();

    }

    @Override
    public double devuelveTotal() {
        return 0;
    }

    @Override
    public void enviarAlertaMinimodeStock(String product, double quantity) {
        String SubjectEmail = "Alerta de Minimo Stock ";
        String BodyEmail = "Le recomendamos compar mas articulos de : " +product+"\n"+
                "Solamente se tienen "+quantity+ "Kg en stock";
        mailService.sendEmail(new MailParts(SubjectEmail,"administrator@test.com",BodyEmail));

    }

    @Override
    public double ventaProductos() {
        return 0;
    }
}
