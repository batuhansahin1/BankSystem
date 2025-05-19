package com.TurkishFinance.bankSystem.webApi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkishFinance.bankSystem.business.abstracts.CentralBankTransactionService;
import com.TurkishFinance.bankSystem.business.requests.CreateCentralBankTransactionRequest;
import com.TurkishFinance.bankSystem.business.responses.AfterCentralBankTransactionResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/centralbank/transaction")
@AllArgsConstructor
public class CentralBankTransactionController {

	private final CentralBankTransactionService centralBankTransactionService;
	
	@PostMapping("/add")
	public AfterCentralBankTransactionResponse add(CreateCentralBankTransactionRequest createCentralBankTransactionRequest) {
		
		return centralBankTransactionService.addTransaction(createCentralBankTransactionRequest);
	}
}
