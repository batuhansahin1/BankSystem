package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCorporateAccountsResponse {

	 private String accountAccountNumber;
	 private String accountIbanNumber;
	 private String accountAccountCurrency;
	 private String accountVergiKimlikNo;
	 private String corporateCorporateName;
	 
	 private String corporateCorporateCustomerNumber;
	
}
