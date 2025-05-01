package com.TurkishFinance.bankSystem.entities.individuals;

import com.TurkishFinance.bankSystem.entities.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class IndividualAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@OneToOne
	@JoinColumn(name = "account_id")
	private Account account ; 
	
	@ManyToOne
	@JoinColumn(name = "individual_customer_id")
	private IndividualCustomer individualCustomer;
}
