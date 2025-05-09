package com.TurkishFinance.bankSystem.business.rules.corporates;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateAccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorporateAccountBusinessRules {

	private CorporateAccountRepository corporateAccountRepository;
	
	public void checkIfAccountNumberExists(String accountNumber) {
		// TODO Auto-generated method stub
		if(!corporateAccountRepository.existsByAccountAccountNumber(accountNumber)) {
			throw new BusinessException("There is no record with that accountNumber in corporateAccounts");
		}
	}

}
