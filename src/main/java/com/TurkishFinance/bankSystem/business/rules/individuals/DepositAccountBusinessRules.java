package com.TurkishFinance.bankSystem.business.rules.individuals;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.DepositAccountRepository;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepositAccountBusinessRules {

	private final DepositAccountRepository depositAccountRepository;

	public void checkIfAccountNumberExists(String accountNumber) {
		if(!depositAccountRepository.existsByAccountNumber(accountNumber)) {
			throw new BusinessException("there is no record with that accountNumber in individual customer");
		}
		
	}

	public void chekIfCustomerExists(String customerNumber) {

		if(!depositAccountRepository.existsByCustomerCustomerNumber(customerNumber)) {
			 throw new BusinessException("There is no customer with that customerNumber pls check and try again");
		}
		
	}

}
