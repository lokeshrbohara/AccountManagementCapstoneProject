package com.example.accountManagement.service;

import com.example.accountManagement.entity.EmailDetails;
public interface EmailService {
	 
    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);
 
    // Method
    // To send an email with attachment
    //String sendMailWithAttachment(EmailDetails details);
}