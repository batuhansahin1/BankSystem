package com.TurkishFinance.bankSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkishFinance.bankSystem.entities.AccountTransaction;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Integer>{

	AccountTransaction getByTransactionNumber(String transactionNumber);
	boolean existsByTransactionNumber(String transactionNumber);
	boolean existsByTransactionReferance(String transactionReferance);

}
