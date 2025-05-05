package com.TurkishFinance.bankSystem.webApi.corporates;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.corporates.CorporateCustomerService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllCorporateCustomersResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateCustomerResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/corporateCustomer")
@AllArgsConstructor
public class CorporateCustomersController {

	private final CorporateCustomerService corporateCustomerService;
	
	@GetMapping("/get")
	public GetCorporateCustomerResponse getCorporateCustomer( long corporateCustomerNumber) {
		
		return corporateCustomerService.getCorporateCustomer(corporateCustomerNumber);
	}
	
	@PostMapping("/add")
	public void add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws Exception {
		
		this.corporateCustomerService.addCorporateCustomer(createCorporateCustomerRequest);
	}
	
    @GetMapping("/getAll")
    public List<GetAllCorporateCustomersResponse> getAll(){
    	return this.corporateCustomerService.getAll();
    }
}
