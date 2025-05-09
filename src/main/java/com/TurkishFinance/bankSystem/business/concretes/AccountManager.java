package com.TurkishFinance.bankSystem.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.abstracts.AccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllAccountsResponse;
import com.TurkishFinance.bankSystem.business.rules.AccountBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountRepository;
import com.TurkishFinance.bankSystem.entities.Account;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountManager implements AccountService {

	private final AccountRepository accountRepository;
	private final AccountBusinessRules accountBusinessRules;
	private final ModelMapperService modelMapperService;
	
	
	
	@Override
	public List<GetAllAccountsResponse> getAll() {
		List<Account> accounts=accountRepository.findAll();
		List<GetAllAccountsResponse> allAccounts=accounts.stream()
				.map(account->this.modelMapperService.forResponse().map(account, GetAllAccountsResponse.class))
				.collect(Collectors.toList());
		
		return allAccounts;
	}



	@Override
	public void add(CreateAccountRequest createAccountRequest) {
		
	}



	@Override
	public void delete(String accountNumber) {
		this.accountRepository.existsByAccountNumber(accountNumber);
		this.accountRepository.deleteByAccountNumber(accountNumber);
		
	}

}
