package com.TurkishFinance.bankSystem.business.abstracts.individuals;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.requests.CreateIndividualAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllIndividualAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetIndividualAccountResponse;

@Service
public interface DepositAccountService {

	
	public List<GetAllIndividualAccountsResponse> getAll();
	public GetIndividualAccountResponse get(String customerNumber);
    public void add(CreateIndividualAccountRequest createIndividualAccountRequest);
    public void delete(String accountNumber);
    
}
