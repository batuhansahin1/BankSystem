package com.TurkishFinance.bankSystem.business.concretes.individuals;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.IndividualAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateIndividualAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllIndividualAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetIndividualAccountResponse;
import com.TurkishFinance.bankSystem.business.rules.individuals.IndividualAccountBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualAccountRepository;
import com.TurkishFinance.bankSystem.entities.Account;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualAccount;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IndividualAccountManager implements IndividualAccountService {

	
	private final IndividualAccountBusinessRules individualAccountBusinessRules;
	private final IndividualAccountRepository individualAccountRepository;
	private final AccountRepository accountRepository;
	private final ModelMapperService modelMapperService;
	
	
	@Override
	public List<GetAllIndividualAccountsResponse> getAll() {
	
		List<IndividualAccount> individualAccounts=this.individualAccountRepository.findAll();
		List<GetAllIndividualAccountsResponse> accountsResponse=individualAccounts.stream()
				.map(account->this.modelMapperService.forResponse().map(account, GetAllIndividualAccountsResponse.class))
				.collect(Collectors.toList());
		
		return accountsResponse;
	}


	@Override
	public GetIndividualAccountResponse get(String customerNumber) {
		this.individualAccountBusinessRules.checkIfAccountNumberExists(customerNumber);
		IndividualAccount individualAccount=this.individualAccountRepository.findByAccountAccountNumber(customerNumber);
		GetIndividualAccountResponse accountResponse=this.modelMapperService.forResponse()
				.map(individualAccount,GetIndividualAccountResponse.class);
		
		
		return accountResponse;
	}


	@Override
	public void add(CreateIndividualAccountRequest createIndividualAccountRequest) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(String customerNumber) {
		this.individualAccountRepository.existsByAccountAccountNumber(customerNumber);
		Account account=accountRepository.findByAccountNumber(customerNumber);
		accountRepository.delete(account);
		
	}

}
