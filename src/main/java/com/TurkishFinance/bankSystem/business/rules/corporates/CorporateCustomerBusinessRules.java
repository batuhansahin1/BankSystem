package com.TurkishFinance.bankSystem.business.rules.corporates;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateCustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorporateCustomerBusinessRules {
	
	private CorporateCustomerRepository corporateCustomerRepository;

	public void checkIfIdExists(int id)  {
		
		if(!this.corporateCustomerRepository.existsById(id)) {
			throw new BusinessException("Bu idye ait kayıt sistemde bulunamadı");
		}
	}
	
	public void checkIfCorporateCustomerNumberExists(String corporateCustomerNumber) {
		
		if(!this.corporateCustomerRepository.existsByCorporateCustomerNumber(corporateCustomerNumber)) {
			throw new BusinessException("Bu customerNumber'a ait ait kayıt sistemde bulunamadı");
		}
	}
    //gerek yok birden fazla corporate ve birden fazla individual'a izin veriyoruz
	public void checkIfTcKimlikNoExists(String tcKimlikNo) {
		if(this.corporateCustomerRepository.existsByCustomerTcKimlikNo(tcKimlikNo)) {
			throw new BusinessException("böyle bir tcye ait corporate customer  oluşturulmuş");
		}
		
}
}
