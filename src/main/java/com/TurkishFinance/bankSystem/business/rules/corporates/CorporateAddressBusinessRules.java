package com.TurkishFinance.bankSystem.business.rules.corporates;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateAddressRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorporateAddressBusinessRules {

	private final CorporateAddressRepository corporateAddressRepository;

	public void checkIfAddressNumberExists(String corporateAddressNumber) {
		if(!corporateAddressRepository.existsByCorporateAddressNumber(corporateAddressNumber)) {
		   throw new BusinessException("there is no record with that corporateAddressNumber in corporateAddress");
		}
	}
}

