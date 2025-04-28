package com.TurkishFinance.bankSystem.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "addresses")
@Entity

public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "city_name")
	private String cityName;
	
	@Column(name = "county_name")
	private String county;
	
	@Column(name = "neighborhood")
	private String neighborhood;
	@Column(name = "street")
	private String street;
	@Column(name = "house_number")
	private int houseNumber;
	@Column(name = "gate_number")
	private int gateNumber;
	
	@OneToMany(mappedBy = "address")
	private List<IndividualCustomer> individualCustomerList;
	
	@OneToOne(mappedBy = "address")
	private CorporateCustomer corporateCustomer;
}
