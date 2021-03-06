package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class SimpleEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

    public void send(final Mail mail) {
        try {
            final SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);

            LOGGER.info("Email has been send, @Tests: shouldSendEmailWithCarbonCoby & shouldSendEmailWithOutCarbonCoby");
        } catch (MailException e) {

            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);

        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {

        return getMailMessage(mail);
    }

    private SimpleMailMessage getMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(ofNullable(mail.getToCc()).orElse(""));
        return mailMessage;
    }
}
