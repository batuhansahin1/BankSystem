package com.TurkishFinance.bankSystem.business.requests;

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
		private Date birthDate;
		
		@NotEmpty
		@NotNull
		@Size(min=11,max = 11)
		private long tcKimlikNo;

	   @Size(min = 10,max = 10)
	   @NotNull
	   @NotEmpty
	   private String vKimlikNo;
	
	   @Size(max = 30)
	   @NotNull
	   @NotEmpty
	   private String corporateName;
	
	   @NotEmpty
	   @NotNull
	   @Size(max = 20)
	   private String companyType;
	
	   @NotEmpty
	   @NotNull
	   private String corporatePhone;
	
	//şimdi customer tablosundan bu tcye ait customer nesnesini çekip bunu CorporateCustomer'daki customer değişkenine atayacağım
//	@NotEmpty
//	@NotNull
//	@Size(min = 11,max = 11)
//	private long customerTcKimlikNo;
}
