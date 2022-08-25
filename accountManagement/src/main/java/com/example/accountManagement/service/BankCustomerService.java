package com.example.accountManagement.service;

import com.example.accountManagement.entity.BankCustomer;

public interface BankCustomerService {
	
	BankCustomer accountCreation(BankCustomer bankCustomer);
	
	BankCustomer getAccountFromPanCard(String panCard);
	
	
}
