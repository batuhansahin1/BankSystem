package com.TurkishFinance.bankSystem.core.utilities.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;



@Configuration
public class AppConfig {

	
	@Bean
	public ModelMapper getModel() {
		return new ModelMapper();
	}
	
	@Bean 
	public WebClient webClient() {
		return WebClient.builder().build();
	}

}
