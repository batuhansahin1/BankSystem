package com.TurkishFinance.bankSystem.business.rules.individuals;

import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualCustomerRepository;

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
