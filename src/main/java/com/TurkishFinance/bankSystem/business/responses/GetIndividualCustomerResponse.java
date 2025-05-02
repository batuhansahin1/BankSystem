package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerResponse {

	private String customerFirstName;
	private String customerLastName;
	private long customerTcKimlikNo;
	private String customerPhoneNumber;
}
