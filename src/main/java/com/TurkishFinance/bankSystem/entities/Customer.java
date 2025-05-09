package com.TurkishFinance.bankSystem.entities;

import java.time.LocalDate;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "customers")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	private LocalDate birthDate;
	
	@Column(name = "tc_kimlik_no",unique = true,length = 11)
	private String tcKimlikNo;
	

	//bunlara gerek olmaz eğer alan istemiyorsam
	@OneToOne(mappedBy ="customer")
	private IndividualCustomer individualCustomer;
	
	@OneToOne(mappedBy = "customer")
	private CorporateCustomer corporateCustomer; 
	
	
}
