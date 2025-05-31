package com.TurkishFinance.bankSystem.entities.corporates;

import com.TurkishFinance.bankSystem.entities.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

//bu class silinecek yerine vadesiz vadeli gram dolar hesabı requestleri alıcak hepsi de
//account'a bağlanıcak
public class DrawingAccount extends Account {
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	 */

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(referencedColumnName = "account_number") private Account account;
	 */
	
//	@ManyToOne
//	@JoinColumn(referencedColumnName =  "corporate_customer_number")
//	private CorporateCustomer corporateCustomer;
//	
	
}
