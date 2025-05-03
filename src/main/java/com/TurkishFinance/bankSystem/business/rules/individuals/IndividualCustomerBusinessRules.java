package com.TurkishFinance.bankSystem.business.rules.individuals;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualCustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {

	private IndividualCustomerRepository individualCustomerRepository;
	
	public void checkIfExistsByCustomerNumber(long individualCustomerNumber) {
		
		if(!this.individualCustomerRepository.existsByIndividualCustomerNumber(individualCustomerNumber)) {
			System.out.println("böyle bir müşteri yok");
		}
	}
	
	public void checkIfTcKimlikNoExists(long tcKimlikNo) {
		
		
	}
}
