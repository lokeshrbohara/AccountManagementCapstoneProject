package com.example.accountManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bankAccount")
public class BankAccount {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountNo")
	private long accountNo;
	
	@Column(name = "customerid", nullable = false)
	private long customerid;
	
	@Column(name = "bankBalance")
	private double bankBalance = 0.0;
	
	@Column(name = "type")
	private String type = "Saving";
	
}