package com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkishFinance.bankSystem.entities.individuals.IndividualAccount;

@Repository
public interface IndividualAccountRepository extends JpaRepository<IndividualAccount, Integer> {

}
