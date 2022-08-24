package com.example.accountManagement.validators;

public class TransactionValidator {

	
	private long fromAccNo;
	private long toAccNo;
	private double amount;
	private double balance;
	public TransactionValidator(long fromAccNo, long toAccNo, double amount, double balance) {
		
		this.fromAccNo = fromAccNo;
		this.toAccNo = toAccNo;
		this.amount = amount;
		this.balance = balance;
		// get the available balance
//		this.balance = customerRepository.
	}
	
	public TransactionResult validate() {
		
		if(amount<0) return new TransactionResult(false, "Amount cannot be less than zero");
//		if(fromAccNo==toAccNo) return new TransactionResult(false, "Cannot send to same account");
		if(balance<amount) return new TransactionResult(false, "Insufficient Balance");
		else return new TransactionResult(true, "Success");
		
	}
	
	
	
}

