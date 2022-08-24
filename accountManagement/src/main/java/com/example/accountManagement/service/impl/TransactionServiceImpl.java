package com.example.accountManagement.service.impl;

import java.util.Date;
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
	
	
	@Override
	public String performTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		long fromAccNumber;
		long toAccNumber;
		double amount;
		double fromBalance;
		double toBalance;
		
		fromAccNumber = transaction.getFromAccNumber();
		toAccNumber = transaction.getToAccNumber();
		amount = transaction.getAmount();
		
		//get balance
		Optional<BankAccount> fromBankAccount = bankAccountRepository.findById(fromAccNumber);
		Optional<BankAccount> toBankAccount = bankAccountRepository.findById(toAccNumber);
		if(fromBankAccount.isPresent() && toBankAccount.isPresent()) {
			fromBalance = fromBankAccount.get().getBankBalance();
			toBalance = toBankAccount.get().getBankBalance();
			
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

	@Override
	public List<Transaction> getTransactionsFromDate(Long accountNo, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return transactionRepository.findTransactionsFromDate(accountNo, fromDate, toDate);
	}

	@Override
	public String performWithdrawal(long accountNo, double amount) {
		// TODO Auto-generated method stub
		double balance;
		Optional<BankAccount> bankAccount = bankAccountRepository.findById(accountNo);
		if(bankAccount.isPresent()) {
			balance = bankAccount.get().getBankBalance();
			
			TransactionValidator validator = new TransactionValidator(accountNo, accountNo, amount, balance);
			TransactionResult result = validator.validate();
			if(result.getFirst()) {
				BankAccount newfrom = bankAccount.get();
				newfrom.setBankBalance(balance-amount);
				bankAccountRepository.save(newfrom);
			}
			Transaction transaction = new Transaction(accountNo, accountNo, amount, result.getSecond(), result.getFirst());
			transactionRepository.save(transaction);
	
			return result.getSecond();
			
		}
		else {
			Transaction transaction = new Transaction(accountNo, accountNo, amount, "Account Number is Wrong", false);
			transactionRepository.save(transaction);
			return "Account Number is Wrong";
		}
	}

	@Override
	public String performDeposit(long accountNo, double amount) {
		// TODO Auto-generated method stub
		double balance;
		Optional<BankAccount> bankAccount = bankAccountRepository.findById(accountNo);
		if(bankAccount.isPresent()) {
			balance = bankAccount.get().getBankBalance();

			BankAccount newfrom = bankAccount.get();
			newfrom.setBankBalance(balance+amount);
			bankAccountRepository.save(newfrom);
			Transaction transaction = new Transaction(accountNo, accountNo, amount, "Amount Deposited", true);
			transactionRepository.save(transaction);
			return "Amount Deposited";
		}
		else{
			Transaction transaction = new Transaction(accountNo, accountNo, amount, "Account Number Incorrect", false);
			transactionRepository.save(transaction);
			return "Account Number Incorrect";
		}
	}

}
