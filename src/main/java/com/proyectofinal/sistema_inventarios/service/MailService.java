package com.proyectofinal.sistema_inventarios.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MailService {
    private String username = "33ed01720c411a";
    private String password = "af46153c1bb0e4";

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host","smtp.mailtrap.io");
        props.put("mail.smtp.port","2525");

        return props;
    }

    public void sendEmail(MailParts mailParts){
        Session session = Session.getInstance(getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        Message message = prepareMessage(session, mailParts);
        try {
            System.out.println(message);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Message prepareMessage(Session session, MailParts mailParts){
        Message message = new MimeMessage(session);
        try {

            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailParts.getRecipient()));
            message.setSubject(mailParts.getSubject());
            message.setText(mailParts.getBody());
            System.out.println(message.getFrom().toString());
            return message;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }


}

