package com.example.accountManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accountManagement.entity.Transaction;
import com.example.accountManagement.service.TransactionService;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public String saveBankAccount(@RequestBody Transaction transaction){
		
		System.out.println(transaction.toString());
	
		return transactionService.performTransaction(transaction);
		
	}
	
	@GetMapping("{accNo}")
	public List<Transaction> getTopTransactions(@PathVariable("accNo") long accNo){
		System.out.println("Acc No: "+accNo);
		return transactionService.getTopTransactions(accNo);
		
	}
	
	@GetMapping("{accNo}/{limit}")
	public List<Transaction> getTop5Transactions(@PathVariable("accNo") long accNo, @PathVariable("limit") int limit){
		System.out.println("Acc No: "+accNo);
		return transactionService.getTop5Transactions(accNo, limit);
		
	}
	
	
	
	
	
}
