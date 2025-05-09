package com.TurkishFinance.bankSystem.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.AccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllAccountsResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/accounts")
@AllArgsConstructor
public class AccountsController {

	private final AccountService accountService;
	
	@GetMapping("/getAll")
	public List<GetAllAccountsResponse> getAll(){
		
		return this.accountService.getAll();
	}
	
	@PostMapping("/add")
	public void add(CreateAccountRequest createAccountRequest) {
		this.accountService.add(createAccountRequest);
	}
	
	@DeleteMapping("/delete")
	public void delete(String accountNumber) {
		accountService.delete(accountNumber);
	}
}
