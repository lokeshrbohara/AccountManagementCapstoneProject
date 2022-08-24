package com.example.accountManagement.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.accountManagement.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	@Query("select u from Transaction u where u.fromAccNumber = :accNo order by timestamp desc")
	List<Transaction> findTop5TransactionByAccNo(@Param("accNo") long accNo, Pageable pageable);
	
	
	@Query("select u from Transaction u where u.fromAccNumber = :accNo order by timestamp desc")
	List<Transaction> findTopTransactionByAccNo(@Param("accNo") long accNo);
	
	


}