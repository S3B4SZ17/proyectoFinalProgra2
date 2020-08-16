package com.proyectofinal.sistema_inventarios.service;


import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Message;

@Service
@AllArgsConstructor
public class MailConfirmation {

    private final TemplateEngine templateEngine;

    public String build(Message message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);

    }
}
