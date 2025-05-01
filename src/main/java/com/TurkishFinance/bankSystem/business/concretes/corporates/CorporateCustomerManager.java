package com.TurkishFinance.bankSystem.business.concretes.corporates;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.abstracts.corporates.CorporateCustomerService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateCustomerResponse;
import com.TurkishFinance.bankSystem.business.rules.corporates.CorporateCustomerBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateCustomerRepository;
import com.TurkishFinance.bankSystem.entities.corporates.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerRepository corporateCustomerReqpository;
	private ModelMapperService modelMapperService;
	private CorporateCustomerBusinessRules corporateCustomerBusinessRules;
	
	@Override
	public GetCorporateCustomerResponse getCorporateCustomer(long corporateCustomerNumber) {
		
		try {
			
			this.corporateCustomerBusinessRules.checkIfCorporateCustomerNumberExists(corporateCustomerNumber);
		}
		catch (Exception e) {
		System.out.println(e.getMessage());
		}
		CorporateCustomer corporateCustomer=this.corporateCustomerReqpository.findByCorporateCustomerNumber(corporateCustomerNumber);
        GetCorporateCustomerResponse customerResponse=this.modelMapperService.forResponse()
        		.map(corporateCustomer, GetCorporateCustomerResponse.class);
                  
		return customerResponse;
	}

	@Override
	public void addCorporateCustomer(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
	
		//burada nüfustan veri doğrulayacağız
		
	}

}
