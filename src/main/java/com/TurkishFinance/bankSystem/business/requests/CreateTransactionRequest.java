package com.TurkishFinance.bankSystem.business.requests;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
     //frontendden gelecek veriler
	 //bunları benim hesabın ibanla birleştirip göndereceğim
	//sender'ı tanımlamak için senderIban da alabiliriz
    //accountNumber'dan sender iban'ı çekeriz
	@Size(min = 16,max = 16)
	private String accountNumber;
	@Size(min = 26,max = 26)
	private String receiverIban;
	
	
	private double transferAmount;
	private String description;
	private String receiverFirstName;
	private String receiverLastName;
}
