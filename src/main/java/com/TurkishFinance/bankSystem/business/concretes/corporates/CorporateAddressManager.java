package com.TurkishFinance.bankSystem.business.concretes.corporates;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.abstracts.corporates.CorporateAddressService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateAddressRequest;

import com.TurkishFinance.bankSystem.business.responses.GetCorporateAddressResponse;
import com.TurkishFinance.bankSystem.business.rules.corporates.CorporateAddressBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateAddressRepository;
import com.TurkishFinance.bankSystem.entities.corporates.CorporateAddress;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorporateAddressManager implements CorporateAddressService {

	private final ModelMapperService modelMapperService;
	private final CorporateAddressRepository corporateAddressRepository;
	private final CorporateAddressBusinessRules corporateAddressBusinessRules;
	
	@Override
	public GetCorporateAddressResponse get(String corporateAddressNumber) {
		corporateAddressBusinessRules.checkIfAddressNumberExists(corporateAddressNumber);
		CorporateAddress corporateAddress=this.corporateAddressRepository
				.findByCorporateAddressNumber(corporateAddressNumber);
	    GetCorporateAddressResponse getAddressResponse=modelMapperService.forResponse()
	    		.map(corporateAddress,GetCorporateAddressResponse.class);
		
		return getAddressResponse ;
	}

	@Override
	public void add(CreateCorporateAddressRequest createCorporateAddressRequest) {
		//adresin kayıtlı olup olmadığını nasıl teyit edicez
		//yarın bakıcam
		
	}
	
	
	
	

}
