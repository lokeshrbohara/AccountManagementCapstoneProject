package com.example.accountManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	
	
}
