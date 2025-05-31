package com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkishFinance.bankSystem.entities.individuals.DepositAccount;

import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;

@Repository
public interface DepositAccountRepository extends JpaRepository<DepositAccount, Integer> {

	
	//boolean existsByAccountAccountNumber(String accountNumber);
	//IndividualAccount findByAccountAccountNumber(String accountNumber);
	//müşterinin hesabı var mı onu döndürüyor yani account tablosunda bu customerNumber a ait kayı var mu
	//bu benim işime yaramaz müşteri hesabı kaydı açarken çünkü bir mişteri birden fazla bireysel hesap açabilir.
	
	//bunlar da hata vermez çünkü accountta customer değişkeni var
	boolean existsByCustomerCustomerNumber(String customerNumber);
	IndividualCustomer findByCustomerCustomerNumber(String customerNumber);
	//IndividualAccount findByAccountNumber(String accountNumber);
	//extend mimarisine geçtikten sonraki sorgular
	boolean existsByAccountNumber(String accountNumber);
	DepositAccount findByAccountNumber(String accountNumber);
	
}
   