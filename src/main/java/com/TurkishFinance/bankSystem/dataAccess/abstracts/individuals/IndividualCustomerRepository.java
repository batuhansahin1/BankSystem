package com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer> {

	boolean existsByIndividualCustomerNumber(String individualCustomerNumber);
    IndividualCustomer findByIndividualCustomerNumber(String individualCustomerNumber);
    boolean existsByCustomerTcKimlikNo(String tcKimlikNo);
}
