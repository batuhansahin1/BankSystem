package com.TurkishFinance.bankSystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "customers")
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	
	@Column(name = "tc_kimlik_no",unique = true,length = 11)
	private long tcKimlikNo;
	
	 
	//bunlara gerek olmaz eğer alan istemiyorsam
	@OneToOne(mappedBy ="customer")
	IndividualCustomer individualCustomer;
	
	@OneToOne(mappedBy = "customer")
	CorporateCustomer corporateCustomer;
	
}
