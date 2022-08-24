package com.example.accountManagement.service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accountManagement.entity.Transaction;
import com.example.accountManagement.repository.TransactionRepository;

@Service
public class CsvExportService {

	@Autowired
    private TransactionRepository transactionRepository;


    public void writeTransactionsToCsv(Writer writer) {

        List<Transaction> transactions = transactionRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
        	csvPrinter.printRecord("Transaction_Id", "Amount", "From_Account_Number", "Result", 
        			"Status", "TimeStamp", "To_Account_Number");
            for (Transaction transaction : transactions) {
                csvPrinter.printRecord(transaction.getTransaction_id(), transaction.getAmount(), 
                		transaction.getFromAccNumber(), transaction.getResult(), transaction.isStatus(), 
                		transaction.getTimestamp(), transaction.getToAccNumber());
            }
        } catch (IOException e) {
            System.out.println("Error While writing CSV "+ e);
        }
    }
    
}
