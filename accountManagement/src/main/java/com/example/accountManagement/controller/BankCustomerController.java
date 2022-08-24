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

import com.example.accountManagement.entity.BankCustomer;
import com.example.accountManagement.service.BankCustomerService;

@RestController
@RequestMapping("/api/bankCustomer")
public class BankCustomerController {
	
	@Autowired
	private BankCustomerService bankCustomerService;
	
	@PostMapping
	public ResponseEntity<BankCustomer> accountCreation(@RequestBody BankCustomer bankCustomer){
		
		System.out.println(bankCustomer.toString());
	
		return new ResponseEntity<BankCustomer>(
				bankCustomerService.accountCreation(bankCustomer), 
				HttpStatus.CREATED
				);
	}
		
	
	@GetMapping("{panCard}")
	public BankCustomer getAccountsWithCustomerId(@PathVariable("panCard") String panCard){
		return bankCustomerService.getAccountFromPanCard(panCard);
	}
	
//	@GetMapping
//	public ResponseEntity<BankCustomer> getAccountFromCustomerId(){
//		return bankCustomerService.getAccountFromCustomerId();
//	}
		
	

	//}
}
