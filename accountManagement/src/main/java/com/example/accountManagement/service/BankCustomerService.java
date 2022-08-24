package com.example.accountManagement.service;


import java.util.List;
import java.util.Optional;

import com.example.accountManagement.entity.BankCustomer;

public interface BankCustomerService {
	
	BankCustomer accountCreation(BankCustomer bankCustomer);
	
	BankCustomer getAccountFromPanCard(String panCard);
	
	
	//List<BankCustomer> getAccountFromcid();


}

