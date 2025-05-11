package com.TurkishFinance.bankSystem.business.concretes.individuals;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.IndividualAddressService;
import com.TurkishFinance.bankSystem.business.responses.GetIndividualAddressResponse;
import com.TurkishFinance.bankSystem.business.rules.individuals.IndividualAddressBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualAddressRepository;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualAddress;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor

public class IndividualAddressManager implements IndividualAddressService {

	//müşteri kaydı  oluşturulurken nüfusdan gidip alıncak ikametgah adresi zaten müşteri bilgileriyle birlikte
	//döndürüyorum
	
	private final ModelMapperService modelMapperService;
	private final IndividualAddressRepository individualAddressRepository;
	private final IndividualAddressBusinessRules individualAddressBusinessRules;
	@Override
	public GetIndividualAddressResponse get(String individualAddressNumber) {
		this.individualAddressBusinessRules.checkIfIndividualAddressNumberExists(individualAddressNumber);
		IndividualAddress individualAddress=this.individualAddressRepository.findByIndividualAddressNumber(individualAddressNumber);
		GetIndividualAddressResponse individualAddressResponse=this.modelMapperService.forResponse()
				.map(individualAddress, GetIndividualAddressResponse.class);
		return individualAddressResponse;
	}
     
}
