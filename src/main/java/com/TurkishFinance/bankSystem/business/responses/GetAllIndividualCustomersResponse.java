package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllIndividualCustomersResponse {

	private String customerFirstName;
	private String customerLastName;
	private String customerTcKimlikNo;
	private String individualCustomerNumber;
	private String phoneNumber;
}
