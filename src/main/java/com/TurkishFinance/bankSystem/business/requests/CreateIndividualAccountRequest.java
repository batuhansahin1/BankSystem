package com.TurkishFinance.bankSystem.business.requests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualAccountRequest {

	private String tcKimlikNo;
	private String firstName;
	private String lastName;
	private LocalDate birthdate;
	private String birthPlace;
}
