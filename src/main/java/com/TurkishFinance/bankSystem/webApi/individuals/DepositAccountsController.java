package com.TurkishFinance.bankSystem.webApi.individuals;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.DepositAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateDepositAccountRequest;

import com.TurkishFinance.bankSystem.business.responses.GetAllDepositAccountsResponse;

import com.TurkishFinance.bankSystem.business.responses.GetDepositAccountResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/individualAccounts")
@AllArgsConstructor
public class DepositAccountsController {

	private final DepositAccountService depositAccountService;
	
	@GetMapping("/getAll")
	public List<GetAllDepositAccountsResponse> getAll(){
		return depositAccountService.getAll();
	}
	@GetMapping("/get/{accountNumber}")
	public GetDepositAccountResponse getDepositAccount(@PathVariable String accountNumber) {
		return this.depositAccountService.get(accountNumber);
	}
	@PostMapping("/add")
	public void add(CreateDepositAccountRequest createDepositAccountRequest) {
		this.depositAccountService.add(createDepositAccountRequest);
	}
	@DeleteMapping("/delete")
	public void delete(String accountNumber) {
		this.depositAccountService.delete(accountNumber);
	}
	
}
