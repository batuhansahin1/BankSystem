package com.TurkishFinance.bankSystem.business.concretes.individuals;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.IndividualCustomerService;
import com.TurkishFinance.bankSystem.business.requests.CreateIndividualCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetIndividualCustomerResponse;
import com.TurkishFinance.bankSystem.business.rules.CustomerBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.individuals.IndividualCustomerBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualCustomerRepository;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	private IndividualCustomerRepository individualCustomerRepository;
	private ModelMapperService modelMapperService;
	private IndividualCustomerBusinessRules individualCustomerBusinessRules;
	private CustomerBusinessRules customerBusinessRules;
	
	@Override
	public GetIndividualCustomerResponse getIndividualCustomer(long individualCustomerNumber) {
		//businessruless
		this.individualCustomerBusinessRules.checkIfExistsByCustomerNumber(individualCustomerNumber);
		
		IndividualCustomer individualCustomer =this.individualCustomerRepository.findByIndividualCustomerNumber(individualCustomerNumber);
		GetIndividualCustomerResponse individualCustomerResponse= this.modelMapperService.forResponse().map(individualCustomer, GetIndividualCustomerResponse.class);
		return individualCustomerResponse;
	}

	@Override
	public void createIndividualCustomer(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		// TODO Auto-generated method stub
		//nüfusa request atılacak veri doğrulanıp devam edilecek
		
		this.customerBusinessRules.checkIfTcKimlikNoExists(createIndividualCustomerRequest.getTcKimlikNo());
		
	}

}
