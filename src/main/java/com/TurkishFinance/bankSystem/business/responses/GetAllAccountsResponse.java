package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAccountsResponse {

	
	private String accountNumber;
	private String ibanNumber;
	private long totalAmount;
}
