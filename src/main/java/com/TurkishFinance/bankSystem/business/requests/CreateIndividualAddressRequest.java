package com.TurkishFinance.bankSystem.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateIndividualAddressRequest {

	private String individualCustomerNumber;
	private String city;
	private String county;
	private String neighborhood;
	private String street;
	private String houseNumber;
	private String gateNumber;
}
