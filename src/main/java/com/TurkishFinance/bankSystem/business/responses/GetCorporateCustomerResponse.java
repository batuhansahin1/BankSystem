package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerResponse {

	//artık extend ediyor ama kimlik bilgileri customer'da değil
	//kimlik bilgileriyle ilgili ayrı bir planım var
	//değiştiriyoruz
	//01.06.2025 uygulamada
	//şimdilik test için sadece individual ile çalışıcam   
	private String customerFirstName;
    private String customerLastName;
    private String corporateName;
    private String corporatePhone;
	private String vergiKimlikNo;
	
}
