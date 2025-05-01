package com.TurkishFinance.bankSystem.business.abstracts.corporates;

import com.TurkishFinance.bankSystem.business.requests.CreateCorporateCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateCustomerResponse;


public interface CorporateCustomerService {

	
	public GetCorporateCustomerResponse getCorporateCustomer(long corporateCustomerNumber);

	public void addCorporateCustomer(CreateCorporateCustomerRequest createCorporateCustomerRequest);
}
