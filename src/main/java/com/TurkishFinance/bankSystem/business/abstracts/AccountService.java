package com.TurkishFinance.bankSystem.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.requests.CreateAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllAccountsResponse;

@Service
public interface AccountService {

	
	List<GetAllAccountsResponse> getAll();

	void add(CreateAccountRequest createAccountRequest);

	void delete(String accountNumber);
}
