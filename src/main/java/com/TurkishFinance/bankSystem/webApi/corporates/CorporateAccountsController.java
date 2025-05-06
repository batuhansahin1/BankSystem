package com.TurkishFinance.bankSystem.webApi.corporates;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.corporates.CorporateAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.requests.UpdateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllCorporateAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateAccountResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/corporateAccountsCustomer")
@AllArgsConstructor
public class CorporateAccountsController {

	private final CorporateAccountService corporateAccountService;
	
	@GetMapping("/get")
	public GetCorporateAccountResponse get(String accountNumber) {

		return this.corporateAccountService.getCorporateAccount(accountNumber);
	}
	
	@GetMapping("/getAll")
	public List<GetAllCorporateAccountsResponse> getAll(){
	
		return this.corporateAccountService.getAll();
	}
	
	@PostMapping("/add")
	public void add(CreateCorporateAccountRequest createCorporateAccountRequest) throws JsonProcessingException {
		
		this.corporateAccountService.add(createCorporateAccountRequest);
	}
	
	@DeleteMapping("delete")
	public void delete(String customerNumber) {
		
		this.corporateAccountService.delete(customerNumber);
	}
	@PutMapping("update")
	public void update(UpdateCorporateAccountRequest updateCorporateAccountRequest) {
		this.corporateAccountService.update(updateCorporateAccountRequest);
	}
}
