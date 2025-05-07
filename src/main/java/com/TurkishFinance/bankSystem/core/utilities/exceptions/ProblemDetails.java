package com.TurkishFinance.bankSystem.core.utilities.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProblemDetails {

	
	private int errorCode;
	private String errorMessage;
	
}
