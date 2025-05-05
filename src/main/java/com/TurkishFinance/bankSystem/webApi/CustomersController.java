package com.TurkishFinance.bankSystem.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.CustomerService;
import com.TurkishFinance.bankSystem.business.responses.GetAllCustomersResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCustomerResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/customers")
@AllArgsConstructor
public class CustomersController {


	private final CustomerService customerService;

	@GetMapping("/get/{id}")
	public GetCustomerResponse get(@PathVariable int id) {
		
		return customerService.get(id);
	}
	
	@GetMapping("/getAll")
	public List<GetAllCustomersResponse> getAll(){
		return this.customerService.getAll();
	}
}
