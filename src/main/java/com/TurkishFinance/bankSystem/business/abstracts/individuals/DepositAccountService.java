package com.TurkishFinance.bankSystem.business.abstracts.individuals;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.requests.CreateDepositAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllDepositAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetDepositAccountResponse;


@Service
public interface DepositAccountService {

	
	public List<GetAllDepositAccountsResponse> getAll();
	public GetDepositAccountResponse get(String accountNumber);
    public void add(CreateDepositAccountRequest createDepositAccountRequest);
    public void delete(String accountNumber);
    
}
