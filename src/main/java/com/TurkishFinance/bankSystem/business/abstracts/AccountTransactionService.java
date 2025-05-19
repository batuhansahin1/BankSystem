package com.TurkishFinance.bankSystem.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.requests.CreateTransactionRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllTransactionsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetTransactionResponse;

@Service
public interface AccountTransactionService {

	public GetTransactionResponse get(String transactionNumber);

	public List<GetAllTransactionsResponse> getAll();
	
	 public void add(CreateTransactionRequest createTransactionRequest);

	
	
}
