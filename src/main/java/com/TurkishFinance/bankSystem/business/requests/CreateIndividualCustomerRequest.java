package com.TurkishFinance.bankSystem.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

	private long tcKimlikNo;
	private String firstName;
	private String lastName;
	private String phoneNumber;
}
