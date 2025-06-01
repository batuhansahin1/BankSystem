package com.TurkishFinance.bankSystem.business.requests;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDrawingAccountRequest {

//buradan da çekebiiliriz corporateCustomer'ı veritabanından bu field ile çekicez 
//diğer her şeyi kendimiz oluşturucaz
	@NotNull
	@NotEmpty
	@Size(min = 13,max = 13)
    private String corporateCustomerNumber;
	
	@Size(min = 2,max = 3)
	private String accountCurrency;
 
    
    
//bu requestte merkez bankasından iban isteyeceğiz bu requestte bizim kişisel bilgiler göndermemiz lazım
//müşteri bizim veritabanımızda olduğu için verilerini veritabanından çekip merkez bankasına göndericez

}
