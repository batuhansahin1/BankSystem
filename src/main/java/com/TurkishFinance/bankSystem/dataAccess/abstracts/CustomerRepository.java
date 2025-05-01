package com.TurkishFinance.bankSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkishFinance.bankSystem.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
