package com.TurkishFinance.bankSystem.business.abstracts;



import com.TurkishFinance.bankSystem.business.requests.CreateCentralBankTransactionRequest;
import com.TurkishFinance.bankSystem.business.responses.AfterCentralBankTransactionResponse;


public interface CentralBankTransactionService {

	public AfterCentralBankTransactionResponse addTransaction(CreateCentralBankTransactionRequest createCentralBankTransactionRequest);
}
