package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCorporateCustomersResponse {

	private String customerFirstName;
	private String customerLastName;
	private String customerTcKimlikNo;
	private String individualCustomerNumber;
	private String corporatePhone;
	private String vergiKimlikNo;
	private String corporateName;
}
