package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerResponse {

	private String customerFirstName;
    private String customerLastName;
    private String corporateName;
    private String corporatePhone;
	private String vKimlikNo;
	
}
