package com.TurkishFinance.bankSystem.entities;

import java.time.LocalDate;
import java.util.Date;

import com.TurkishFinance.bankSystem.entities.corporates.CorporateAccount;
import com.TurkishFinance.bankSystem.entities.corporates.CorporateCustomer;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualAccount;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "accounts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//bu classın hepsini biz oluşturacağımız için ilişkilendirilen customer'ın numarasını alsak yeterli
public class Account {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "account_number",unique = true)
	@Size(min = 16,max = 16)
	//biz oluşturucaz
    private String accountNumber;
    
	
	@Column(name = "opened_date")
	//biz oluşturucaz
    private LocalDate openedDate;
	
	@Column(name = "total_amount")
	//biz oluşturuaz
	private long totalAmount;
	
	@Column(name = "account_status")
	private String status;
	
	@Column(name = "iban_number")
	//merkez bankasından alıcaz
	private String ibanNumber;
	
	
	@Column(name = "account_currency")
	@Size(min = 2,max = 3)
	private String accountCurrency;
	
	@OneToOne(mappedBy = "account",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private IndividualAccount individualAccount;
	
	@OneToOne(mappedBy = "account",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private CorporateAccount corporateAccount;
	
	//customerların bağlı olduğu ana class için tc ile çekip merkez bankasında yollicaz 
	//o da doğru mu diye kontrol edicek
	
	//ikiye ayrılacak individualCustomerAccount,CorporateCustomerAccount diye çünkü aynı address gibi olucaktı
	//2 customer'ın field'ı var biri boş kalıcak
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "individual_customer_id") private IndividualCustomer
	 * individualCustomer;
	 */
	

}
