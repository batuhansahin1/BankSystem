package com.TurkishFinance.bankSystem.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Table(name = "individual_customers")
@Entity

public class IndividualCustomer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "individual_customer_number")
	private long individualCustomerNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    
    @Column(name = "birth_place")
    private String birthPlace;
	
	@Column(name="birth_date")
	private Date birthDate;
	
	@Column(name="residential_city")
	private String residentialCity;
	@Column(name = "residential_country")
	private String residentialCounty;
	@Column(name = "residential_detail")
	private String residentialDetail;
	
	
	@OneToMany(mappedBy = "individualCustomer")
	List<PersonAccount> personAccountList;
	
	//bir person hem persona hem de corporate customer olabilir
	@OneToOne
	@JoinColumn(referencedColumnName = "tc_kimlik_no")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	
}
