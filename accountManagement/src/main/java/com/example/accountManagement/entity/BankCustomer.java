package com.example.accountManagement.entity;

import lombok.NoArgsConstructor;

import java.lang.String;
import java.util.*;

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
	
	@Column(name = "postalAddress")
	private String postalAddress;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "dob")
	private String dob;
	


	
	
	
	@Override
	public String toString() {
		return "BankCustomer [CustomerID=" + c_id + ", aadhar=" + aadhar + ", panCard=" + panCard
				+ ", name=" + name + ",postalAddress=" + postalAddress + ",email=" + email + ", dob=" + dob+ "]";
	}

	public long getC_id() {
		return c_id;
	}

	public void setC_id(long c_id) {
		this.c_id = c_id;
	}

	public long getAadhar() {
		return aadhar;
	}

	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

}
