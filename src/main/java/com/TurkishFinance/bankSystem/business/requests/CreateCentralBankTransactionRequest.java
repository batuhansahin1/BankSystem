package com.TurkishFinance.bankSystem.business.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCentralBankTransactionRequest {

	private String transactionReferance;
	
	private String senderFirstName;
	private String senderLastName;
	private String senderIban;
	private String receiverIban;
	private String receiverFirstName;
	private String receiverLastName;
	private String description;
	private String currency;
	private double transferAmount;
	private String senderBankName;
}
