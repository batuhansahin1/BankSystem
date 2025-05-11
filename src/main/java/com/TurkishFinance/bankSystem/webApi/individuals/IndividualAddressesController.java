package com.TurkishFinance.bankSystem.webApi.individuals;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.IndividualAddressService;
import com.TurkishFinance.bankSystem.business.requests.CreateIndividualAddressRequest;
import com.TurkishFinance.bankSystem.business.responses.GetIndividualAddressResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/individualAddresses")
@AllArgsConstructor
public class IndividualAddressesController {

	
	private final IndividualAddressService individualAddressService;
	
	@GetMapping("/get")
	public GetIndividualAddressResponse get(String individualAddressNumber) {
		return individualAddressService.get(individualAddressNumber);
	}
	
	@PostMapping("/add")
	public void add(CreateIndividualAddressRequest createIndividualAddrssRequest) {
		
	}
}
