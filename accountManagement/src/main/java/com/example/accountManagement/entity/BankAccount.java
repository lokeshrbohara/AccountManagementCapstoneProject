package com.example.accountManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name="seq2", initialValue=1000000001, allocationSize=1)
@Table(name="bankAccount")
public class BankAccount {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq2")
	@Column(name = "accountNo")
	private long accountNo;
	
	@Column(name = "customerid", nullable = false)
	private long customerid;
	
	@Column(name = "bankBalance")
	private double bankBalance = 0.0;
	
	@Column(name = "type")
	private String type = "Saving";
	
	

	@Override
	public String toString() {
		return "BankAccount [accountNo=" + accountNo + ", customerid=" + customerid + ", bankBalance=" + bankBalance
				+ ", type=" + type + "]";
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	public double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
