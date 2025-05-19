package com.TurkishFinance.bankSystem.business.rules;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountTransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountTransactionBusinessRules {

	
	private final AccountTransactionRepository accountTransactionRepository;

	public void checkIfTransactionNumberExists(String transactionNumber) {
		if(!accountTransactionRepository.existsByTransactionNumber(transactionNumber)) {
			throw new BusinessException("thre is no record with that transaction number");
		}
		
	}

	public void checkIfTransactionReferanceExists(String transactionReferance) {
		
		if(accountTransactionRepository.existsByTransactionReferance(transactionReferance)) {
			throw new BusinessException("this transaction already created");
		}
		
	}
}
