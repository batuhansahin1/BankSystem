package com.TurkishFinance.bankSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkishFinance.bankSystem.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
