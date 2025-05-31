package com.TurkishFinance.bankSystem.business.rules.corporates;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;

import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.DrawingAccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DrawingAccountBusinessRules {

	private final DrawingAccountRepository drawingAccountRepository;
	
	public void checkIfAccountNumberExists(String accountNumber) {
		// TODO Auto-generated method stub
		if(!drawingAccountRepository.existsByAccountNumber(accountNumber)) {
			throw new BusinessException("There is no record with that accountNumber in corporateAccounts");
		}
	}

}
