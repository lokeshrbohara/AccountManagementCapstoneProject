package com.example.accountManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.accountManagement.entity.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{

	@Query("select u from BankAccount u where u.customerid = :customerId")
	List<BankAccount> findAccountsFromCustomerId(long customerId);

	
}
