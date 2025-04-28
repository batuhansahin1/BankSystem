package com.TurkishFinance.bankSystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "corporate_customers")
@Entity
 
public class CorporateCustomer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "v_kimlik_no",length = 10)
	private String vKimlikNo;
	
	@Column(name = "corporate_name")
	private String corporateName;
	
	@Column(name="company_type")
	private String companyType;
	@Column(name = "corporate_customer_number")
	private long corporateCustomerNumber;
	@Column(name = "corporate_phone")
	private String corporatePhone;
	
	
	@OneToOne
	@JoinColumn(referencedColumnName = "tc_kimlik_no")
	private Customer customer;
    @OneToOne
    @JoinColumn(name = "address_id")
   private Address address;

}
