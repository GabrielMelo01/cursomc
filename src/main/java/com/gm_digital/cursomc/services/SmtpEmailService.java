package com.gm_digital.cursomc.services;

import com.gm_digital.cursomc.resources.utils.SmtpAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private JavaMailSender mailSender;

    SmtpAuthenticator authentication = new SmtpAuthenticator();
    javax.mail.Message msg = new MimeMessage(Session
            .getDefaultInstance(System.getProperties(), authentication));


    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando email...");
        mailSender.send(msg);
        LOG.info("Email enviado");
    }
}