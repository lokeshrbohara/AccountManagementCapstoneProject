package com.example.accountManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.accountManagement.entity.BankAccount;
import com.example.accountManagement.entity.Transaction;
import com.example.accountManagement.repository.BankAccountRepository;
import com.example.accountManagement.repository.TransactionRepository;
import com.example.accountManagement.service.TransactionService;
import com.example.accountManagement.validators.TransactionResult;
import com.example.accountManagement.validators.TransactionValidator;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
    private BankAccountRepository bankAccountRepository;
	
	@Autowired
    private TransactionRepository transactionRepository;
	
	private long fromAccNumber;
	private long toAccNumber;
	private double amount;
	private double fromBalance;
	private double toBalance;
	
	@Override
	public String performTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		this.fromAccNumber = transaction.getFromAccNumber();
		this.toAccNumber = transaction.getToAccNumber();
		this.amount = transaction.getAmount();
		
		//get balance
		Optional<BankAccount> fromBankAccount = bankAccountRepository.findById(fromAccNumber);
		Optional<BankAccount> toBankAccount = bankAccountRepository.findById(toAccNumber);
		if(fromBankAccount.isPresent() && toBankAccount.isPresent()) {
			this.fromBalance = fromBankAccount.get().getBankBalance();
			this.toBalance = toBankAccount.get().getBankBalance();
			
			TransactionValidator validator = new TransactionValidator(fromAccNumber, toAccNumber, amount, fromBalance);
			TransactionResult result = validator.validate();
			if(result.getFirst()) {
				BankAccount newfrom = fromBankAccount.get();
				newfrom.setBankBalance(fromBalance-amount);
				bankAccountRepository.save(newfrom);
				
				BankAccount newto = toBankAccount.get();
				newto.setBankBalance(toBalance+amount);
				bankAccountRepository.save(newfrom);
				
			}
			transaction.setStatus(result.getFirst());
			transaction.setResult(result.getSecond());
			transactionRepository.save(transaction);
	
			return result.getSecond();
		}
		else {
			transaction.setStatus(false);
			transaction.setResult("Account Number is Wrong");
			transactionRepository.save(transaction);
			return "Account Number is Wrong";
		}
			
	}

	@Override
	public List<Transaction> getTop5Transactions(long accNo, int limit) {
		// TODO Auto-generated method stub
		
		return transactionRepository.findTop5TransactionByAccNo(accNo, PageRequest.of(0, limit));
	}

	@Override
	public List<Transaction> getTopTransactions(long accNo) {
		// TODO Auto-generated method stub
		
		return transactionRepository.findTopTransactionByAccNo(accNo);
	}

}
