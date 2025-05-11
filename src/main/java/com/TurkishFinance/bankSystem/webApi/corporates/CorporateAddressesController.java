package com.TurkishFinance.bankSystem.webApi.corporates;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.corporates.CorporateAddressService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateAddressRequest;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateAddressResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/corporateAddressesCustomer")
@AllArgsConstructor
public class CorporateAddressesController {

	private final CorporateAddressService corporateAddressService;
	
	@GetMapping("/get")
	public GetCorporateAddressResponse get(String corporateAddressNumber) {
		
		
		return corporateAddressService.get(corporateAddressNumber);
		
	}
	
	@PostMapping("/add")
	public void add(CreateCorporateAddressRequest createCorporateAddressRequest) {
		corporateAddressService.add(createCorporateAddressRequest);
	}
}
