package com.example.accountManagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name="transactions")
public class Transaction {


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private long transaction_id;
	
	@Column(name = "from_acc_number")
	private long fromAccNumber;
	
	@Column(name = "to_acc_number")
	private long toAccNumber;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "timestamp", updatable = false)
	@CreationTimestamp
	private Date timestamp;
	
	@Column(name = "result")
	private String result;
	
	@Column(name = "status")
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public long getFromAccNumber() {
		return fromAccNumber;
	}

	public void setFromAccNumber(long fromAccNumber) {
		this.fromAccNumber = fromAccNumber;
	}

	public long getToAccNumber() {
		return toAccNumber;
	}

	public void setToAccNumber(long toAccNumber) {
		this.toAccNumber = toAccNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", fromAccNumber=" + fromAccNumber + ", toAccNumber="
				+ toAccNumber + ", amount=" + amount + ", timestamp=" + timestamp + "]";
	}

	
	public Transaction(long fromAccNumber, long toAccNumber, double amount, String result, boolean status) {
//		super();
		this.fromAccNumber = fromAccNumber;
		this.toAccNumber = toAccNumber;
		this.amount = amount;
		this.result = result;
		this.status = status;
	}
	
	


	
}
