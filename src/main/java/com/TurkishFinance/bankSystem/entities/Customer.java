package com.TurkishFinance.bankSystem.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.TurkishFinance.bankSystem.entities.corporates.CorporateCustomer;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "customers")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//hesabın sahibinin kimlik bilgileri ortak alanlar olduğu için customer içinde
	
	@Size(min = 12,max = 12)
	@Column(name = "customer_number")
	private String customerNumber;
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "customer")
	List<Account> accounts;
	@OneToMany(mappedBy = "customer")
	List<Address> addresses;

	//bunlara gerek olmaz eğer alan istemiyorsam
	
//	@OneToOne(mappedBy ="customer")
//	private IndividualCustomer individualCustomer;
//	
//	@OneToOne(mappedBy = "customer")
//	private CorporateCustomer corporateCustomer; 
	
	
}
