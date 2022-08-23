package com.example.accountManagement.service;

import java.util.List;
import java.util.Optional;

import com.example.accountManagement.entity.BankAccount;

public interface BankAccountService {

	BankAccount saveAccount(BankAccount bankAccount);
	
	Optional<BankAccount> getAccountFromAccountNo(long accountNo);
	
	List<BankAccount> getAllAccounts();
}
