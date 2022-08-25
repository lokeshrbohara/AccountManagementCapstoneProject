package com.example.accountManagement.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.accountManagement.entity.BankCustomer;

@Repository
public interface BankCustomerRepository extends JpaRepository<BankCustomer, Long>{
	
	@Query("select u from BankCustomer u where u.panCard = :panCard")
	BankCustomer findAccountsFromPanCard(String panCard);

}
 