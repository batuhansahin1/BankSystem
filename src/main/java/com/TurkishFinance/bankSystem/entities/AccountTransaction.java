package com.TurkishFinance.bankSystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "account_transactions")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//merkez bankasından döndürülecek
	//merkez bankasından gelen responseda gönderilen banka da yer alacak
	@Column(name = "transaction_number")
	private String transactionNumber;
	
	@Column(name = "transfer_amount")
	private double transferAmount;
	
	@Column(name = "sender_iban")
	private String senderIban;
	
	@Column(name = "receiver_bank_name")
	private String receiverBankName;
	@Column(name = "receiver_iban")
	private String receiverIban;
	
	@Column(name = "sender_first_name")
	private String senderFirstName;
	
	@Column(name = "sender_last_name")
	private String senderLastName;
	
	@Column(name = "receiver_first_name")
	private String receiverFirstName;
	@Column(name = "receiver_last_name")
	private String receiverLastName;
	
	@Column(name = "direction")
	private String direction;
	@Column(name = "currency")
	private String currency;
	// referansı aynı olanlar aynı işlem oluyor yani alıcıya ve göndericiye verilen transaction numarası gibi
	@Column(name = "transaction_referance")
	private String transactionReferance;
	
	@Column(name = "description")
	private String description;
	
    @ManyToOne
    @JoinColumn(referencedColumnName = "account_number")
    private Account account;
	
}
