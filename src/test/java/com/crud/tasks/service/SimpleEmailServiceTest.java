package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    SimpleEmailService simpleEmailService;
    @Mock
    JavaMailSender javaMailSender;               // --> public interface JavaMailSender extends MailSender {

    @Test                                        // <-- Kodilla test
    public void shouldSendEmail() {
        // Given
        Mail mail = new Mail("test@test.com", "Test", "Test message","");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCc());
        // When
        simpleEmailService.send(mail);

        // Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }

    @Test
    public void shouldSendEmailWithCarbonCoby() {
        // Given
        Mail mail = new Mail("test@test.com", "Test", "Test message","CarbonCobyTest@test.com");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCc());

        // When
        simpleEmailService.send(mail);

        // Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }

    @Test
    public void shouldSendEmailWithOutCarbonCoby() {
        // Given
        Mail mail = new Mail("test@test.com", "Test", "Test message",null);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(ofNullable(mail.getToCc()).orElse(""));

        // When
        simpleEmailService.send(mail);

        // Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}