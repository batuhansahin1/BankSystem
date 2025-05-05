package com.TurkishFinance.bankSystem.webApi.individuals;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.IndividualCustomerService;
import com.TurkishFinance.bankSystem.business.requests.CreateIndividualCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllIndividualCustomersResponse;
import com.TurkishFinance.bankSystem.business.responses.GetIndividualCustomerResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/individualCustomer")
@AllArgsConstructor
public class IndividualCustomersController {

	private IndividualCustomerService individualCustomerService;
	
	@GetMapping("/get")
	public GetIndividualCustomerResponse getIndividualCustomer(long individualCustomerNumber) {
		
		return this.individualCustomerService.getIndividualCustomer(individualCustomerNumber);
	}
	
	@PostMapping("/add")
	public void addIndividualCustomer(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws Exception {
		this.individualCustomerService.createIndividualCustomer(createIndividualCustomerRequest);
	}
	@GetMapping("/getAll")
	public List<GetAllIndividualCustomersResponse> getAll(){
		return this.individualCustomerService.getAll();
	}
}
