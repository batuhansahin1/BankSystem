package com.TurkishFinance.bankSystem.entities;

import java.util.Date;

import com.TurkishFinance.bankSystem.entities.corporates.CorporateCustomer;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;

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
	
	//hesabın sahibinin kimlik bilgileri ortak alanlar olduğu için customer içinde
	
    @Column(name = "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    
   
    @Column(name = "birth_place")
    private String birthPlace;
	
	@Column(name="birth_date")
	private Date birthDate;
	
	@Column(name = "tc_kimlik_no",unique = true,length = 11)
	private long tcKimlikNo;
	

	//bunlara gerek olmaz eğer alan istemiyorsam
	@OneToOne(mappedBy ="customer")
	private IndividualCustomer individualCustomer;
	
	@OneToOne(mappedBy = "customer")
	private CorporateCustomer corporateCustomer; 
	
	
}
