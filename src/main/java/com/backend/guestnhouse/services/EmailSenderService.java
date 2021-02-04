package com.backend.guestnhouse.services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.backend.guestnhouse.payload.request.EmailConfig;

@Service("emailSenderService")
public class EmailSenderService {

    private JavaMailSenderImpl javaMailSender;
    
	
	@Autowired
	EmailConfig emailConfig;

    @Autowired
    public EmailSenderService(JavaMailSenderImpl javaMailSender) {
    	 
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) throws InterruptedException {
    	Properties props = new Properties();
 		props.put("mail.smtp.auth", "true");
 		props.put("mail.smtp.starttls.enable", "true");
 		props.put("mail.smtp.host", "smtp.gmail.com");
 		props.put("mail.smtp.port", "587");
 		props.put("mail.smtp.ssl.trust", "*");
 	// Create a mail sender
        javaMailSender.setHost(emailConfig.getHost());
        javaMailSender.setPort(emailConfig.getPort());
        javaMailSender.setUsername(emailConfig.getUsername());
        javaMailSender.setPassword(emailConfig.getPassword());
        javaMailSender.setJavaMailProperties(props);
        javaMailSender.send(email);
    }
}