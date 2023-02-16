package com.gm_digital.cursomc.services;

import com.gm_digital.cursomc.domain.Cliente;
import com.gm_digital.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
