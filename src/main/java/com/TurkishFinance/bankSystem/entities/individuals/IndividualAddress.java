package com.TurkishFinance.bankSystem.entities.individuals;

import com.TurkishFinance.bankSystem.entities.Address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;


@Entity
public class IndividualAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 6,max = 6)
	@JoinColumn(name = "individual_address_number")
	private String individualAddressNumber;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "individual_customer_number")
	private IndividualCustomer individualCustomer;
    
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address; 
	
}
