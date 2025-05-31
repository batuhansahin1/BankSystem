package com.TurkishFinance.bankSystem.webApi.individuals;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.DepositAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateIndividualAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllIndividualAccountsResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/individualAccounts")
@AllArgsConstructor
public class DepositAccountsController {

	private final DepositAccountService depositAccountService;
	
	@GetMapping("/getAll")
	List<GetAllIndividualAccountsResponse> getAll(){
		return depositAccountService.getAll();
	}
	@PostMapping("/add")
	public void add(CreateIndividualAccountRequest createIndividualAccountRequest) {
		this.depositAccountService.add(createIndividualAccountRequest);
	}
	@DeleteMapping("/delete")
	public void delete(String accountNumber) {
		this.depositAccountService.delete(accountNumber);
	}
	
}
