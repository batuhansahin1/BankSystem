package com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkishFinance.bankSystem.entities.corporates.CorporateAddress;

@Repository
public interface CorporateAddressRepository extends JpaRepository<CorporateAddress, Integer> {

	CorporateAddress findByCorporateAddressNumber(String corporateAddressNumber);
	boolean existsByCorporateAddressNumber(String corporateAddressNumber);

}
