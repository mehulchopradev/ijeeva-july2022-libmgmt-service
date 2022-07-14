package com.ijeeva.libmgmtservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String emailAddress) {
        // emulate that
        /* try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } */

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setFrom("mehul.chopra.dev@gmail.com");
        simpleMailMessage.setSubject("Test spring mail");
        simpleMailMessage.setText("this is to test the spring email function");
        this.javaMailSender.send(simpleMailMessage);

        System.out.println("Email sent!!!!");
    }
}
