package com.TurkishFinance.bankSystem.business.abstracts.corporates;

import java.util.List;


import com.TurkishFinance.bankSystem.business.requests.CreateDrawingAccountRequest;

import com.TurkishFinance.bankSystem.business.requests.UpdateDrawingAccountRequest;

import com.TurkishFinance.bankSystem.business.responses.GetAllDrawingAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetDrawingAccountResponse;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface DrawingAccountService {

	GetDrawingAccountResponse getCorporateAccount(String accountNumber);

	List<GetAllDrawingAccountsResponse> getAll();

	void add(CreateDrawingAccountRequest createDrawingAccountRequest) throws JsonProcessingException, Exception;

	//delete'yi de request ile yapabiliriz ama gerek yok 
	void delete(String customerNumber);

	void update(UpdateDrawingAccountRequest updateDrawingAccountRequest);
}
