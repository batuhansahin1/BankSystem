package com.TurkishFinance.bankSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.core.utilities.exceptions.ProblemDetails;

import io.swagger.v3.oas.annotations.Hidden;

@SpringBootApplication
public class BankSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSystemApplication.class, args);
	}



  
}
