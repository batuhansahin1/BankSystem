package com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.entities.individuals.IndividualAddress;

@Service
public interface IndividualAddressRepository extends JpaRepository<IndividualAddress, Integer> {

}
