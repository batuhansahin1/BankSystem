package com.TurkishFinance.bankSystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "account_transactions")
@Entity
public class AccountTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "sender_iban")
	private String senderIban;
	
	@Column(name = "receiver_iban")
	//gönderici hesap alıcı hesap da yapabiliriz
	private String receiverIban;
	
	@Column(name = "description")
	private String description;
	
    
	
}
