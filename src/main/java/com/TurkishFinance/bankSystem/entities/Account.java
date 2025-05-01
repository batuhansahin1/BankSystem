package com.TurkishFinance.bankSystem.entities;

import java.util.Date;

import com.TurkishFinance.bankSystem.entities.corporates.CorporateAccount;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualAccount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "accounts")
@Entity
public class Account {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "account_number")
    private int accountNumber;
    
	
	@Column(name = "opened_date")
    private Date openedDate;
	
	@Column(name = "total_amount")
	private long totalAmount;
	
	@Column(name = "account_status")
	private String status;
	
	@Column(name = "iban_number")
	private String ibanNumber;
	
	@OneToOne(mappedBy = "account")
	private IndividualAccount individualAccount;
	
	@OneToOne(mappedBy = "account")
	private CorporateAccount corporateAccount;
	
	//ikiye ayrılacak individualCustomerAccount,CorporateCustomerAccount diye çünkü aynı address gibi olucaktı
	//2 customer'ın field'ı var biri boş kalıcak
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "individual_customer_id") private IndividualCustomer
	 * individualCustomer;
	 */
	

}
