package com.TurkishFinance.bankSystem.entities.individuals;

import com.TurkishFinance.bankSystem.entities.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositAccount extends Account{

	private double rate;
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	 */
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(referencedColumnName = "account_number") private Account account
	 * ;
	 */
	
//	@ManyToOne
//	@JoinColumn(referencedColumnName =  "individual_customer_number")
//	private IndividualCustomer individualCustomer;
}
