package com.example.accountManagement.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.accountManagement.entity.BankCustomer;
import com.example.accountManagement.repository.BankCustomerRepository;
import com.example.accountManagement.service.BankCustomerService;

@Service
public class BankCustomerServiceImpl implements BankCustomerService {
	
	@Autowired
	private BankCustomerRepository bankCustomerRepository;
	
	
	@Override
	public BankCustomer accountCreation(BankCustomer bankCustomer) {
		// TODO Auto-generated method stub
		return bankCustomerRepository.save(bankCustomer);
	}

	@Override
	public BankCustomer getAccountFromPanCard(String panCard) {
		// TODO Auto-generated method stub
		return bankCustomerRepository.findAccountsFromPanCard(panCard);
	}

//	@Override
//	public List<BankCustomer> getAccountFromcid() {
//		// TODO Auto-generated method stub
//		return bankCustomerRepository.findAll();
//	}

}
