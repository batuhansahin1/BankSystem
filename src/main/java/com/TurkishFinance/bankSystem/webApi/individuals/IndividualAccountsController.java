package com.TurkishFinance.bankSystem.webApi.individuals;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.IndividualAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateIndividualAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllIndividualAccountsResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/individualAccounts")
@AllArgsConstructor
public class IndividualAccountsController {

	private final IndividualAccountService individualAccountService;
	
	@GetMapping("/getAll")
	List<GetAllIndividualAccountsResponse> getAll(String accountNumber){
		return individualAccountService.getAll();
	}
	@PostMapping("/add")
	public void add(CreateIndividualAccountRequest createIndividualAccountRequest) {
		this.individualAccountService.add(createIndividualAccountRequest);
	}
	@DeleteMapping("/delete")
	public void delete(String accountNumber) {
		this.individualAccountService.delete(accountNumber);
	}
	
}
