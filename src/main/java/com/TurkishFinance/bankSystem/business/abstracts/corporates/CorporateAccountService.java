package com.TurkishFinance.bankSystem.business.abstracts.corporates;

import java.util.List;

import com.TurkishFinance.bankSystem.business.requests.CreateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.requests.UpdateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllCorporateAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateAccountResponse;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface CorporateAccountService {

	GetCorporateAccountResponse getCorporateAccount(String accountNumber);

	List<GetAllCorporateAccountsResponse> getAll();

	void add(CreateCorporateAccountRequest createCorporateAccountRequest) throws JsonProcessingException, Exception;

	//delete'yi de request ile yapabiliriz ama gerek yok 
	void delete(String customerNumber);

	void update(UpdateCorporateAccountRequest updateCoporateAccountRequest);
}
