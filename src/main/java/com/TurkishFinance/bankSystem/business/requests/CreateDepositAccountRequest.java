package com.TurkishFinance.bankSystem.business.requests;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepositAccountRequest {

	@Size(min = 12,max = 12)
	private String individualCustomerNumber;
	
	@Size(min = 2,max = 3)
	private String accountCurrency;
}
