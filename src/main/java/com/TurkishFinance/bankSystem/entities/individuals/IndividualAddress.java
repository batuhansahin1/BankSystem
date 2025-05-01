package com.TurkishFinance.bankSystem.entities.individuals;

import com.TurkishFinance.bankSystem.entities.Address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class IndividualAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name = "individual_customer_id")
	private IndividualCustomer individualCustomer;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address; 
	
}
