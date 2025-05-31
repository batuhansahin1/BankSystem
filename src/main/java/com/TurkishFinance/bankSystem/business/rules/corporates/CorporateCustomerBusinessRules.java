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
	
	public void checkIfCorporateCustomerNumberNotExists(String customerNumber) {
		
		if(this.corporateCustomerRepository.existsByCustomerNumber(customerNumber)) {
			throw new BusinessException("This customer number already exists. Please enter different one");
		}
	}
	
	public void checkIfCorporateCustomerNumberExists(String corporateCustomerNumber) {
		
		if(!this.corporateCustomerRepository.existsByCustomerNumber(corporateCustomerNumber)) {
			throw new BusinessException("Bu customerNumber'a ait ait kayıt sistemde bulunamadı");
		}
	}
    //gerek yok birden fazla corporate ve birden fazla individual'a izin veriyoruz
	//buna gerek yok çünkü coporate kaydederken tcKimlik no almıyoruz
//	public void checkIfTcKimlikNoExists(String tcKimlikNo) {
//		if(this.corporateCustomerRepository.existsByCustomerTcKimlikNo(tcKimlikNo)) {
//			throw new BusinessException("böyle bir tcye ait corporate customer  oluşturulmuş");
//		}
//		
//}

	public void checkIfVergiKimlikNoExists(String vergiKimlikNo) {
		if(this.corporateCustomerRepository.existsByVergiKimlikNo(vergiKimlikNo)) {
			throw new BusinessException("there is an account with that vkn .Please  try different one");
		}
	}
}
