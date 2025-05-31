package com.TurkishFinance.bankSystem.entities.corporates;

import java.util.List;

import com.TurkishFinance.bankSystem.entities.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "corporate_customers")
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CorporateCustomer extends Customer {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	@Column(name = "v_kimlik_no",length = 10)
	private String vergiKimlikNo;
	
	@Column(name = "corporate_name")
	private String corporateName;
	
	
	@Column(name="company_type")
	private String companyType;
	
//	@Column(name = "corporate_customer_number",unique = true)
//	@Size(min = 13,max = 13)
//	private String corporateCustomerNumber;
	@Column(name = "corporate_phone")
	private String corporatePhone;
	
	//keyfine yazdık pk refransı da verebilirdik sadece tcKimlikNoyu burada da tutup diğer tabloya join atmamak
	//için bu kodu yazdık
//	@OneToOne
//	@JoinColumn(referencedColumnName = "tc_kimlik_no")
//	private Customer customer;
	
	//ana tabloya referans verip corporateTürlerini sileceğiz
	//direkt account olarak oluşturacağız ana tabloda

	
}
