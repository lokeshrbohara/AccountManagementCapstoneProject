package com.example.accountManagement.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accountManagement.entity.Transaction;
import com.example.accountManagement.misc.AmountID;
import com.example.accountManagement.service.CsvExportService;
import com.example.accountManagement.service.TransactionService;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CsvExportService csvExportService;
	
	
	@PostMapping
	public String performTransactions(@RequestBody Transaction transaction){
		
		System.out.println(transaction.toString());
	
		return transactionService.performTransaction(transaction);
		
	}
	
	@PostMapping("/withdraw")
	public String performWithdrawal(@RequestBody AmountID amountid){
			
		return transactionService.performWithdrawal(amountid.accountNo, amountid.amount);
		
	}
	
	@PostMapping("/deposit")
	public String performDeposit(@RequestBody AmountID amountid){

		return transactionService.performDeposit(amountid.accountNo, amountid.amount);
		
	}
	
	@GetMapping("/getCSV")
	public void getAllTransactionsInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"transactions.csv\"");
        csvExportService.writeTransactionsToCsv(servletResponse.getWriter());
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
	
	@GetMapping("/date")
	public List<Transaction> getTransactionsFromDate(@RequestParam Long accountNo, @RequestParam String fromDate, @RequestParam String toDate) throws ParseException{
		System.out.println("Date: "+fromDate);
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
		Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
		return transactionService.getTransactionsFromDate(accountNo, date1, date2);
	}
	
	
	
	
	
}

