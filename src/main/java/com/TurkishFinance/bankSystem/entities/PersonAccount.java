package com.TurkishFinance.bankSystem.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "person_accounts")
@Entity
public class PersonAccount {

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
	
	@ManyToOne
	@JoinColumn(name = "individual_customer_id")
	private IndividualCustomer individualCustomer;
	

}
