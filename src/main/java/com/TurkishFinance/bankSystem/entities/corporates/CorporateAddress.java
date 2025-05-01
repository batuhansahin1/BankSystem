package com.TurkishFinance.bankSystem.entities.corporates;


import com.TurkishFinance.bankSystem.entities.Address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "corporate_addresses")
@Entity
public class CorporateAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	
	@ManyToOne
	@JoinColumn(name = "corporate_customer_id")
	private CorporateCustomer corporateCustomer;
	

}
