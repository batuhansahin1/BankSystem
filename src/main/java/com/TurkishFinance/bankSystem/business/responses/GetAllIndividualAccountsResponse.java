package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllIndividualAccountsResponse {

	
	private String individualCustomerTcKimlikNo;
	private String accountIbanNumber;
	private long accountTotalAmount;
	private String individualCustomerIndividualCustomerNumber;
	private String accountNumber;
}
