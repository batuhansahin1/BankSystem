package com.TurkishFinance.bankSystem.business.rules.individuals;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualCustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {

	private IndividualCustomerRepository individualCustomerRepository;
	
	public void checkIfExistsByCustomerNumber(String individualCustomerNumber) {
		
		if(!this.individualCustomerRepository.existsByIndividualCustomerNumber(individualCustomerNumber)) {
			System.out.println("böyle bir müşteri yok");
		}
	}
	


	public void checkIfTcKimlikNoExists(String tcKimlikNo) {
		
		if(individualCustomerRepository.existsByCustomerTcKimlikNo(tcKimlikNo)) {
			
			throw new BusinessException("There is an individualCustomer with that tcKimlikNo you can not create one more");
		}
	}
}
