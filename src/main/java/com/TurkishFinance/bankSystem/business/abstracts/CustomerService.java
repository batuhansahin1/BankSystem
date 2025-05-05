package com.TurkishFinance.bankSystem.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.responses.GetAllCustomersResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCustomerResponse;

@Service
public interface CustomerService {

	
	public GetCustomerResponse get(int id);
	
	public List<GetAllCustomersResponse> getAll();
}
