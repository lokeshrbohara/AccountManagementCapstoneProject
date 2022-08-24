package com.example.accountManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accountManagement.entity.BankAccount;
import com.example.accountManagement.repository.BankAccountRepository;
import com.example.accountManagement.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService{

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	
	@Override
	public BankAccount saveAccount(BankAccount bankAccount) {
		// TODO Auto-generated method stub
		return bankAccountRepository.save(bankAccount);
	}


	@Override
	public List<BankAccount> getAllAccounts() {
		// TODO Auto-generated method stub
		return bankAccountRepository.findAll();
	}


	@Override
	public List<BankAccount> getAccountFromCustomerId(long customerId) {
		// TODO Auto-generated method stub
		
		return bankAccountRepository.findAccountsFromCustomerId(customerId);
	}

}
