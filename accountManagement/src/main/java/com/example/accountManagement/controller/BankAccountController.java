package com.example.accountManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accountManagement.service.BankAccountService;

@RestController
@RequestMapping("/bankAcc")
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;
	
	@PostMapping
	public ResponseEntity<BankAccount>
	
}
