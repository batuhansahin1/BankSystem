package com.TurkishFinance.bankSystem.business.rules.corporates;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateCustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorporateCustomerBusinessRules {
	
	private CorporateCustomerRepository corporateCustomerRepository;

	public void checkIfIdExists(int id) throws Exception {
		
		if(!this.corporateCustomerRepository.existsById(id)) {
			throw new Exception("Bu idye ait kayıt sistemde bulunamadı");
		}
	}
	
	public void checkIfCorporateCustomerNumberExists(long corporateCustomerNumber) throws Exception {
		
		if(!this.corporateCustomerRepository.existsByCorporateCustomerNumber(corporateCustomerNumber)) {
			throw new Exception("Bu idye ait kayıt sistemde bulunamadı");
		}
	}
}
