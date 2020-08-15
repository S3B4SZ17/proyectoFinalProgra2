package com.proyectofinal.sistema_inventarios.DAO;

import com.proyectofinal.sistema_inventarios.repository.SalesRepo;
import com.proyectofinal.sistema_inventarios.service.MailParts;
import com.proyectofinal.sistema_inventarios.service.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicInteger;

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
    public int validarExistencias(int id) {
        String sql = "SELECT quantity,name FROM Products WHERE idProducts =" + id;
        AtomicInteger result = new AtomicInteger();
        ResultSetExtractor<Integer> extractor = (ResultSet resultSet) ->  {
                if (resultSet.next()) {
                    double quantity = resultSet.getDouble("quantity");
                    String product = resultSet.getString("name");
                    if (quantity>0){
                        System.out.println("mas que 1");
                        System.out.println(product);
                        result.set(1);
                        if(quantity<10){
                            enviarAlertaMinimodeStock(product,quantity);
                        }
                    }
                }
                return null;
        };

        jdbcTemplate.query(sql, extractor);
        return result.intValue();

    }

    @Override
    public double devuelveTotal() {
        return 0;
    }

    @Override
    public void enviarAlertaMinimodeStock(String product, double quantity) {
        String SubjectEmail = "Alerta de Minimo Stock ";
        String BodyEmail = "Le recomendamos compar mas articulos de :" +product+"\n"+
                "Solamente se tienen "+quantity+ " en stock";
        mailService.sendEmail(new MailParts(SubjectEmail,"sebas@test.com",BodyEmail));

    }


}
