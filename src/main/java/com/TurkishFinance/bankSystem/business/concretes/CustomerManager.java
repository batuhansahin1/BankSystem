package com.TurkishFinance.bankSystem.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.abstracts.CustomerService;
import com.TurkishFinance.bankSystem.business.responses.GetAllCustomersResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCustomerResponse;
import com.TurkishFinance.bankSystem.business.rules.CustomerBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.CustomerRepository;
import com.TurkishFinance.bankSystem.entities.Customer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerBusinessRules customerBusinessRules;
	private final ModelMapperService modelMapper;
	
	@Override
	public GetCustomerResponse get(int id) {
		// TODO Auto-generated method stub
		
		this.customerBusinessRules.checkIfIdExists(id);
		Customer customer=this.customerRepository.findById(id).orElseThrow();
		GetCustomerResponse getCustomerResponse=this.modelMapper.forResponse().map(customer, GetCustomerResponse.class);
		return getCustomerResponse;
	}

	@Override
	public List<GetAllCustomersResponse> getAll() {
		List<Customer> customerList= this.customerRepository.findAll();
		List<GetAllCustomersResponse> getAllCustomersResponse=customerList.stream()
				.map(customer->this.modelMapper.forResponse().map(customer, GetAllCustomersResponse.class)).collect(Collectors.toList());
		
		return getAllCustomersResponse;
	}

	
	
}
