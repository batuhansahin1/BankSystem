package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetTransactionResponse {

	private double transactionAmount;
	private String senderIbanNumber;
	private String receiverIbanNumber;
	private String receiverFirstName;
	private String receiverLastName;
	
	
}
