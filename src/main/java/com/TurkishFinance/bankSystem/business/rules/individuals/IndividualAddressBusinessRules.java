package com.TurkishFinance.bankSystem.business.rules.individuals;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualAddressRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IndividualAddressBusinessRules {

	private final IndividualAddressRepository individualAddressRepository;
	public void checkIfIndividualAddressNumberExists(String individualAddressNumber) {
		
		if(!individualAddressRepository.existsByIndividualAddressNumber(individualAddressNumber)) {
			throw new BusinessException("There is no record with that individualAddressNumber ind individualAddress");
		}
	}
}
