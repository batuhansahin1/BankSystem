package com.TurkishFinance.bankSystem.business.rules.individuals;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualCustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {

	private IndividualCustomerRepository individualCustomerRepository;
	
	
	public void checkIfCustomerNumberExists(String individualCustomerNumber) {
		
		if(!this.individualCustomerRepository.existsByCustomerNumber(individualCustomerNumber)) {
			throw new BusinessException("this customer number not exists in our system.Please try another one");
		}
	}
	
    public void checkIfCustomerNumberNotExists(String customerNumber) {
    	if(this.individualCustomerRepository.existsByCustomerNumber(customerNumber)) {
    		throw new BusinessException("this customer number exists.Please try another one");
    	}
    }

	public void checkIfTcKimlikNoExists(String tcKimlikNo) {
		
		if(individualCustomerRepository.existsByTcKimlikNo(tcKimlikNo)) {
			
			throw new BusinessException("There is an individualCustomer with that tcKimlikNo you can not create one more");
		}
	}
}
