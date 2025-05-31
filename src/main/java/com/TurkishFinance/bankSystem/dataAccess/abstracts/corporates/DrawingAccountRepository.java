package com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.TurkishFinance.bankSystem.entities.corporates.DrawingAccount;

@Repository
public interface DrawingAccountRepository extends JpaRepository<DrawingAccount, Integer> {

	//CorporateAccount findByAccountAccountNumber(String accountNumber);
	
	//void deleteByAccountAccountNumber(String accountNumber);

	//boolean existsByAccountAccountNumber(String accountNumber);
	
	//extend yapısından sonra
	boolean existsByAccountNumber(String accountNumber);
	DrawingAccount findByAccountNumber(String accountNumber);
	
}
