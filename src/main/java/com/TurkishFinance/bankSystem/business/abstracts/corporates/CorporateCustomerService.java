package com.TurkishFinance.bankSystem.business.abstracts.corporates;

import java.util.List;

import com.TurkishFinance.bankSystem.business.requests.CreateCorporateCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllCorporateCustomersResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateCustomerResponse;


public interface CorporateCustomerService {

	
	public GetCorporateCustomerResponse getCorporateCustomer(long corporateCustomerNumber);

	public void addCorporateCustomer(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws Exception;

	public List<GetAllCorporateCustomersResponse> getAll();
}
