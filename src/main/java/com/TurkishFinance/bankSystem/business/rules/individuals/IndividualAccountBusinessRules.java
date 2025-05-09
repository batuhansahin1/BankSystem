package com.TurkishFinance.bankSystem.business.rules.individuals;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualAccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IndividualAccountBusinessRules {

	private final IndividualAccountRepository individualAccountRepository;

	public void checkIfAccountNumberExists(String accountNumber) {
		if(!individualAccountRepository.existsByAccountAccountNumber(accountNumber)) {
			throw new BusinessException("there is no record with that accountNumber in individual customer");
		}
		
	}

}
