package com.example.accountManagement.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Lob;


import java.lang.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bankCustomer")
public class BankCustomer {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_id")
	private long c_id;
	
	@Column(name = "aadhar")
	private long aadhar;
	
	@Column(name = "panCard")
	private String panCard;
	
	@Column(name = "name")
	private String name;
	
	@Lob
	@Column(name = "postalAddress")
	private String postalAddress;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "dob")
	private String dob;
	
	
	

}
