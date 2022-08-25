package com.example.accountManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

 

import com.example.accountManagement.entity.EmailDetails;
import com.example.accountManagement.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired 
	private JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") private String sender;
 
    
    
	public String sendSimpleMail(EmailDetails details) {
		// TODO Auto-generated method stub
		try {
			 
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
	}

}
