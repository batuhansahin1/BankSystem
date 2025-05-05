package com.TurkishFinance.bankSystem.business.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateAccountRequest {

	
    private String corporateCustomerNumber;
    
//bu requestte merkez bankasından iban isteyeceğiz bu requestte bizim kişisel bilgiler göndermemiz lazım	
}
