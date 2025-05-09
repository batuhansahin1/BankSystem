package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetIndividualAccountResponse {

	private String firstName;
	private String lastName;
	private String accountIbanNumber;
	private long accountTotalAmount;
}
