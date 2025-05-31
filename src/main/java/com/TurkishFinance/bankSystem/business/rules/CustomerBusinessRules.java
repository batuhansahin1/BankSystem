package com.TurkishFinance.bankSystem.business.rules;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {

	private final CustomerRepository customerRepository;
	
//	public void checkIfTcKimlikNoExists(String tcKimlikNo) {
//		
//		if(this.customerRepository.existsByTcKimlikNo(tcKimlikNo)) {
//			System.out.println("bu tcye ait bir customer var kayıt alınamaz");
//			throw new BusinessException("bu tcye ait bir customer var kayıt alınamaz");
//		}
//		
//	}

	public void checkIfIdExists(int id) {
		if(!this.customerRepository.existsById(id)) {
			System.out.println("bu id'ye ait bir customer  kaydı yok");
		}
		
	}
	
}
