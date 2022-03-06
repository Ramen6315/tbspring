package com.example.tbspring;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public interface MailSender {
    void send(SimpleMailMessage simpleMailMessage) throws MailException;
    void send(SimpleMailMessage[] simpleMailMessages) throws MailException;
}
