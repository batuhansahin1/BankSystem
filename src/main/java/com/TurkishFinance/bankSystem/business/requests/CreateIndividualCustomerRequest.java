package com.TurkishFinance.bankSystem.business.requests;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

	@NotNull
	@NotEmpty
	@Size(min = 11,max = 11)
	private String tcKimlikNo;
	
	@NotNull
	@NotEmpty
	@Size(max = 25)
	private String firstName;
	@NotNull
	@NotEmpty
	@Size(max = 25)
	private String lastName;
	@NotNull
	@NotEmpty
	@Size(min = 13,max = 13)
	private String phoneNumber;
	
	@NotNull
	@NotEmpty
	private String customerBirthPlace;
	@NotEmpty
	@NotNull
	private LocalDate customerBirthDate;
}
