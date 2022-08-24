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

import com.example.accountManagement.entity.BankAccount;
import com.example.accountManagement.service.BankAccountService;

@RestController
@RequestMapping("/api/bankAcc")
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;
	
	@PostMapping
	public ResponseEntity<BankAccount> saveBankAccount(@RequestBody BankAccount bankAccount){
		
		System.out.println(bankAccount.toString());
	
		return new ResponseEntity<BankAccount>(
				bankAccountService.saveAccount(bankAccount), 
				HttpStatus.CREATED
				);
		
	}
	
	@GetMapping
	public List<BankAccount> getAllAccounts(){
		return bankAccountService.getAllAccounts();
	}
	
//	@GetMapping
//	public ResponseEntity<BankAccount> getAccountfromAccountNo(@PathVariable("accNo") long accNo){
//		return new ResponseEntity<BankAccount>(
//				bankAccountService.getAccountFromAccountNo(accNo),
//				HttpStatus.OK
//				);
		
//	}
	
}