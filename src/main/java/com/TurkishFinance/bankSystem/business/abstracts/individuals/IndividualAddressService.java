package com.TurkishFinance.bankSystem.business.abstracts.individuals;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.responses.GetIndividualAddressResponse;



@Service
public interface IndividualAddressService {

	GetIndividualAddressResponse get(String individualAddressNumber);


}
