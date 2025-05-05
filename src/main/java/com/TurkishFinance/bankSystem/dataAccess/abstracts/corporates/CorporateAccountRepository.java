package com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkishFinance.bankSystem.entities.corporates.CorporateAccount;

@Repository
public interface CorporateAccountRepository extends JpaRepository<CorporateAccount, Integer> {

	
}
