package com.TurkishFinance.bankSystem.entities.individuals;

import java.time.LocalDate;
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
 
@Table(name = "individual_customers")
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class IndividualCustomer extends Customer {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
    @Column(name = "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    
    @Column(name = "birth_place")
    private String birthPlace;
	
	@Column(name="birth_date")
	private LocalDate birthDate;
	
	@Column(name = "tc_kimlik_no",unique = true,length = 11)
	private String tcKimlikNo;
	
//	@Column(name = "individual_customer_number",unique = true)
//	@Size(min = 12,max = 12)
//	private String individualCustomerNumber;
	
	@Column(name = "phone_number")
	@Size(min =12 ,max=14)
	private String phoneNumber;
	
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(referencedColumnName = "tc_kimlik_no") private Customer customer;
	 */
	
	//ikisini de ana tabloya referans vericem

	
	
	//adresste olması lazım güzelim adre tablosuna eklicez daha sonra oradan type'a göre çekeriz zaten response
	//gelince elle set ederiz
	/*
	 * @Column(name="residential_city") private String residentialCity;
	 * 
	 * @Column(name = "residential_country") private String residentialCounty;
	 * 
	 * @Column(name = "residential_detail") private String residentialDetail;
	 */
	
	//direkt idlerini customerId diye referans da alabilirdik aynı idye sahip olanlar aynı muşterinin farklı 
	//hesabı gibi olurdu ama mantıksız
	
	//@OneToMany(mappedBy = "individualCustomer") hesaplar da ikiye ayrılacak
	//private List<PersonAccount> personAccountList;
	
	//bir person hem persona hem de corporate customer olabilir
	
	//addresid'Yi buraya eklemek saçma olur bir müşterinin bu tabloda 1 kaydı var max 1 adres eklerim 
	//ama address tablosuna customer eklersem hem corporate hem de individual customer eklerim alan
	//sanırım bu işe yaramıcak çünkü IndividualCustomer'da Corporate Customer da customer ile ISA ilişkisine
	//sahip değil gelince halledicem
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "address_id") private Address address;
	 */
	
	
}
