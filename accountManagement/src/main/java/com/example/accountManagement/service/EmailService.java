package com.example.accountManagement.service;

import com.example.accountManagement.entity.EmailDetails;

public interface EmailService {
	String sendSimpleMail(EmailDetails details);
}
