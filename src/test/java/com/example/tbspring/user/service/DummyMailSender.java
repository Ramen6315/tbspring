package com.example.tbspring.user.service;

import com.example.tbspring.MailSender;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

public class DummyMailSender implements MailSender {
    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage[] simpleMailMessages) throws MailException {

    }
}
