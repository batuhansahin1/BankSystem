package com.TurkishFinance.bankSystem.business.abstracts.individuals;

import java.util.List;

import com.TurkishFinance.bankSystem.business.requests.CreateIndividualCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllIndividualCustomersResponse;
import com.TurkishFinance.bankSystem.business.responses.GetIndividualCustomerResponse;




public interface IndividualCustomerService {

	GetIndividualCustomerResponse getIndividualCustomer(long individualCustomerNumber);

	void createIndividualCustomer(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws Exception;
    List<GetAllIndividualCustomersResponse> getAll();
}
