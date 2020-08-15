package com.proyectofinal.sistema_inventarios.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final String sender = "springreddit@email.com";
    private final MailConfirmation mailConfirmation;

    @Async
//with EnableSaync is to comunicate with the API much faster
    public void sendMail(MailParts mailParts) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(sender);
            messageHelper.setTo(mailParts.getRecipient());
            messageHelper.setSubject(mailParts.getSubject());
            messageHelper.setText(mailConfirmation.build(mailParts.getBody()));
           // messageHelper.addAttachment();
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Correo con la factura enviada");
        } catch (MailException e) {
            System.err.println("Exception occured when sending mail to " + mailParts.getRecipient() + e);


        }


    }
}
