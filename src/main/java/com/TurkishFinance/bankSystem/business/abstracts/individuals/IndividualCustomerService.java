package com.TurkishFinance.bankSystem.business.abstracts.individuals;

import com.TurkishFinance.bankSystem.business.requests.CreateIndividualCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetIndividualCustomerResponse;




public interface IndividualCustomerService {

	GetIndividualCustomerResponse getIndividualCustomer(long individualCustomerNumber);

	void createIndividualCustomer(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws Exception;

}
