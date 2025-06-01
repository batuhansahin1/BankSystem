package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllIndividualCustomersResponse {

	//ilişki olmadığı için customerFirstName null dönüyor
	//veritabanındaki kayıtlarda individual customerda firstName olmadığı için null dönüyor
	
	private String firstName;
	private String lastName;
	private String tcKimlikNo;
	//extend ettiği için customer number
	private String customerNumber;
	private String phoneNumber;
}
