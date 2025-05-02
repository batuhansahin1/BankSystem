package com.TurkishFinance.bankSystem.business.rules;

import com.TurkishFinance.bankSystem.dataAccess.abstracts.CustomerRepository;

public class CustomerBusinessRules {

	private CustomerRepository customerRepository;
	
	public void checkIfTcKimlikNoExists(long tcKimlikNo) {
		
		if(!this.customerRepository.existsByTcKimlikNo(tcKimlikNo)) {
			System.out.println("bu tcye ait bir customer yok");
		}
		
	}
	
}
