package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllCustomersResponse {

	private int id;
	private String firstName;
	private String lastName;
	private String tcKimlikNo;
	
}
