package com.example.accountManagement.service;

import java.util.Date;
import java.util.List;

import com.example.accountManagement.entity.Transaction;

public interface TransactionService {

	String performTransaction(Transaction transaction);
	List<Transaction> getTop5Transactions(long accNo, int limit);
	List<Transaction> getTopTransactions(long accNo);
	List<Transaction> getTransactionsFromDate(Long accountNo, Date fromDate, Date toDate);
	String performWithdrawal(long accountNo, double amount);
	String performDeposit(long accountNo, double amount);

}
