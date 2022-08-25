package com.example.accountManagement.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.accountManagement.entity.BankAccount;
import com.example.accountManagement.entity.BankCustomer;
import com.example.accountManagement.entity.EmailDetails;
import com.example.accountManagement.entity.Transaction;
import com.example.accountManagement.repository.BankAccountRepository;
import com.example.accountManagement.repository.BankCustomerRepository;
import com.example.accountManagement.repository.TransactionRepository;
import com.example.accountManagement.service.EmailService;
import com.example.accountManagement.service.TransactionService;
import com.example.accountManagement.validators.TransactionResult;
import com.example.accountManagement.validators.TransactionValidator;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
    private BankAccountRepository bankAccountRepository;
	
	@Autowired
    private BankCustomerRepository bankCustomerRepository;
	
	
	@Autowired
    private TransactionRepository transactionRepository;
	
	@Autowired
	private EmailService emailService;
	
	
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
			
	//			//Send Mail
				Optional<BankCustomer> fromBankCustomer = bankCustomerRepository.findById(fromBankAccount.get().getCustomerid());
				Optional<BankCustomer> toBankCustomer = bankCustomerRepository.findById(toBankAccount.get().getCustomerid());
	
				if(fromBankCustomer.isPresent() && toBankCustomer.isPresent()) {
					System.out.println("Sending mail");
					String email = fromBankCustomer.get().getEmail();
					EmailDetails emailDetails = new EmailDetails(email, result.getSecond()+". Amount "+amount+
							" transferred to "+toBankCustomer.get().getEmail()+ ", Account number: "+
							toBankAccount.get().getAccountNo()+
							". If not performed by you please contact your nearest branch at the earliest.", 
							"Transaction Status");
					emailService.sendSimpleMail(emailDetails);
					
					EmailDetails emailDetails2 = new EmailDetails(toBankCustomer.get().getEmail(), result.getSecond()+". Amount "+amount+
							" credited from "+fromBankCustomer.get().getEmail()+ ", Account number: "+
							fromBankAccount.get().getAccountNo(), 
							"Amount received");
					emailService.sendSimpleMail(emailDetails2);
				}
				System.out.println("Outside");
	//			EmailDetails emailDetails = new EmailDetails()
//			
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
				
				Optional<BankCustomer> fromBankCustomer = bankCustomerRepository.findById(bankAccount.get().getCustomerid());

				if(fromBankCustomer.isPresent()) {
					System.out.println("Sending mail");
					String email = fromBankCustomer.get().getEmail();
					EmailDetails emailDetails = new EmailDetails(email, result.getSecond()+". Amount "+amount+
							" withdrawn from you Account Number: "+bankAccount.get().getAccountNo()+
							". If not performed by you please contact your nearest branch at the earliest.", 
							"Withdraw Transaction Status");
					emailService.sendSimpleMail(emailDetails);
				}
				System.out.println("Outside");
			}
			Transaction transaction = new Transaction(accountNo, accountNo, amount, result.getSecond(), result.getFirst());
			transactionRepository.save(transaction);
			
//			//Send Mail
//			EmailDetails emailDetails = new EmailDetails()
			//Send Mail
			
			
	
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
			
			//Send Mail
//			EmailDetails emailDetails = new EmailDetails()
			Optional<BankCustomer> fromBankCustomer = bankCustomerRepository.findById(bankAccount.get().getCustomerid());

			if(fromBankCustomer.isPresent()) {
				System.out.println("Sending mail");
				String email = fromBankCustomer.get().getEmail();
				EmailDetails emailDetails = new EmailDetails(email, "Amount "+amount+
						" credited to your Account Number: "+bankAccount.get().getAccountNo(), "Deposit Transaction Status");
				emailService.sendSimpleMail(emailDetails);
			}
			System.out.println("Outside");
			
	
					
			return "Amount Deposited";
		}
		else{
			Transaction transaction = new Transaction(accountNo, accountNo, amount, "Account Number Incorrect", false);
			transactionRepository.save(transaction);
			return "Account Number Incorrect";
		}
	}

}
