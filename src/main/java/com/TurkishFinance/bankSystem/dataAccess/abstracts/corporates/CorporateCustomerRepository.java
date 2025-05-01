package com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkishFinance.bankSystem.entities.corporates.CorporateCustomer;

@Repository
public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, Integer> {

	
	boolean existsByCorporateCustomerNumber(long corporateCustomerNumber);
	CorporateCustomer findByCorporateCustomerNumber(long corporateCustomerNumber);
}
