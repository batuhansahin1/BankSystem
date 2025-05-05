package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetCorporateAccountResponse {

	//mapper ile yapmıcam elle yapıcam
	private String firstName;
	private String lastName;
	private String accountNumber;
	private String accountIbanNumber;
	private long accountTotalAmount;
}
