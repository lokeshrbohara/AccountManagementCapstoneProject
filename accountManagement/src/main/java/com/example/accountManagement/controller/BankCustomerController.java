package com.example.accountManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/bankAcc")
public class BankCustomerController {
	
	@Autowired
	private BankCustomerSerivce bankCustomerService;
	
	@GetMapping
	public ResponseEntity<BankAccount>

}
