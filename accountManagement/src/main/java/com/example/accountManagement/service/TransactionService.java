package com.example.accountManagement.service;

import java.util.List;

import com.example.accountManagement.entity.Transaction;

public interface TransactionService {

	String performTransaction(Transaction transaction);
	List<Transaction> getTop5Transactions(long accNo, int limit);
	List<Transaction> getTopTransactions(long accNo);

}
