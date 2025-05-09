package com.TurkishFinance.bankSystem.business.rules;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountRepository;
import com.TurkishFinance.bankSystem.entities.Account;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountBusinessRules { 

	private final AccountRepository accountRepository;
	
	public void checkIfAccountNumberExists(String accountNumber) {
		if(!accountRepository.existsByAccountNumber(accountNumber)) {
			throw new BusinessException("There is no record with that account number");
		}
	}
	public void chickIfAccountIsPassive(String accountNumber) {
		Account account=accountRepository.findByAccountNumber(accountNumber);
		if(account.getStatus()=="passive") {
			throw new BusinessException("this account is passive you can not send money to this account");
		}
	}
}
