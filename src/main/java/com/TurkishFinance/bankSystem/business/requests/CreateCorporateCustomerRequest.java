package com.TurkishFinance.bankSystem.business.requests;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
	
	    @NotEmpty
	    @NotNull
	    @Size(max = 20)
	    private String firstName;
	   
	    @NotEmpty
	    @NotNull
	    @Size(max = 20)
	    private String lastName;
	    
	   
	    @NotNull
	    @NotEmpty
	    @Size(max = 20)
	    private String birthPlace;
		
		@NotEmpty
		@NotNull
		private LocalDate birthDate;
		
		@NotEmpty
		@NotNull
		@Size(min=11,max = 11)
		private String tcKimlikNo;

	   @Size(min = 10,max = 10)
	   @NotNull
	   @NotEmpty
	   private String vergiKimlikNo;
	
	   @Size(max = 30)
	   @NotNull
	   @NotEmpty
	   private String corporateName;
	
	   @NotEmpty
	   @NotNull
	   @Size(max = 20)
	   private String companyType;
	
	   //alan koduyla birlikte normalde kurumsal da kişisel de telefon 10 haneli 0 hariç amaalan koduyla +90
	   //3 tane ekleniyo
	   @NotEmpty
	   @NotNull
	   @Size(min = 13,max = 13)
	   private String corporatePhone;
	
	//şimdi customer tablosundan bu tcye ait customer nesnesini çekip bunu CorporateCustomer'daki customer değişkenine atayacağım
//	@NotEmpty
//	@NotNull
//	@Size(min = 11,max = 11)
//	private long customerTcKimlikNo;
}
