package com.TurkishFinance.bankSystem.webApi.corporates;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.corporates.CorporateCustomerService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateCustomerResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/corporateCustomer")
@AllArgsConstructor
public class CorporateCustomersController {

	private CorporateCustomerService corporateCustomerService;
	
	@GetMapping("/get")
	public GetCorporateCustomerResponse getCorporateCustomer( long corporateCustomerNumber) {
		
		return corporateCustomerService.getCorporateCustomer(corporateCustomerNumber);
	}
	
	public void add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		
		this.corporateCustomerService.addCorporateCustomer(createCorporateCustomerRequest);
	}
}
