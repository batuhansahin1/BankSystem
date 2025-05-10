package com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkishFinance.bankSystem.entities.individuals.IndividualAccount;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;

@Repository
public interface IndividualAccountRepository extends JpaRepository<IndividualAccount, Integer> {

	
	boolean existsByAccountAccountNumber(String accountNumber);
	IndividualAccount findByAccountAccountNumber(String accountNumber);
	//müşterinin hesabı var mı onu döndürüyor yani account tablosunda bu customerNumber a ait kayı var mu
	//bu benim işime yaramaz müşteri hesabı kaydı açarken çünkü bir mişteri birden fazla bireysel hesap açabilir.
	boolean existsByIndividualCustomerIndividualCustomerNumber(String customerNumber);
	IndividualCustomer findByIndividualCustomerIndividualCustomerNumber(String customerNumber);
	
}
