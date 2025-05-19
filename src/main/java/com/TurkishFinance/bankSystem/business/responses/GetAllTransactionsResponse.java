package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTransactionsResponse {

	
	private double transfernAmount;
	private String senderIbanNumber;
	private String receiverIbanNumber;
	private String receiverFirstName;
	private String receiverLastName;
}
