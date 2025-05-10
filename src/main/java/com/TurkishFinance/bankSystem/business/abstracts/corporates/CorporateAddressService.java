package com.TurkishFinance.bankSystem.business.abstracts.corporates;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.responses.GetAddressResponse;



@Service
public interface CorporateAddressService {

	GetAddressResponse get(String corporateAddressNumber);

}
