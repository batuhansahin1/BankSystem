package com.TurkishFinance.bankSystem.core.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails getBusinessException(BusinessException exception) {
		ProblemDetails problemDetails=new ProblemDetails();
		problemDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
		problemDetails.setErrorMessage(exception.getMessage());
		return problemDetails;
	}
}
