package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllIndividualAccountsResponse {

	
	private int id;
	private String individualCustomerTcKimlikNo;
	private String ibanNumber;
	private long accountTotalAmount;
	private String individualCustomerIndividualCustomerNumber;
	private String accountNumber;
}
