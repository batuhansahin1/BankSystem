package com.TurkishFinance.bankSystem.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.TurkishFinance.bankSystem.business.abstracts.AccountTransactionService;
import com.TurkishFinance.bankSystem.business.requests.CreateTransactionRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllTransactionsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetTransactionResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/accounttransactions")
@AllArgsConstructor
public class AccountTransactionsController {

	private final AccountTransactionService accountTransactionService;
	
	@GetMapping("/get")
	public GetTransactionResponse get(String transactionNumber) {
		
		return accountTransactionService.get(transactionNumber);
	}
	
	@GetMapping("/getAll")
	public List<GetAllTransactionsResponse> getAll(){
		
		return accountTransactionService.getAll();
		
	}
	@PostMapping("/add")
	public void add(CreateTransactionRequest createTransactionRequest) {
		this.accountTransactionService.add(createTransactionRequest);
	}
	
}
