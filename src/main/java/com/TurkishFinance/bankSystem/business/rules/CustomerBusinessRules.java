package com.TurkishFinance.bankSystem.business.rules;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.dataAccess.abstracts.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {

	private final CustomerRepository customerRepository;
	
	public void checkIfTcKimlikNoExists(String tcKimlikNo) {
		
		if(this.customerRepository.existsByTcKimlikNo(tcKimlikNo)) {
			System.out.println("bu tcye ait bir customer var kayıt alınamaz");
		}
		
	}
	
}
