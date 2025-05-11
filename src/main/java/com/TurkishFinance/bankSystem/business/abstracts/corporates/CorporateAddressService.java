package com.TurkishFinance.bankSystem.business.abstracts.corporates;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.requests.CreateCorporateAddressRequest;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateAddressResponse;



@Service
public interface CorporateAddressService {

	GetCorporateAddressResponse get(String corporateAddressNumber);

	void add(CreateCorporateAddressRequest createCorporateAddressRequest);

}
