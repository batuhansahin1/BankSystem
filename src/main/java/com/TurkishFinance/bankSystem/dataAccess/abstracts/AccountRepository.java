package com.TurkishFinance.bankSystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkishFinance.bankSystem.entities.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	boolean existsByAccountNumber(String accountNumber);
    Account findByAccountNumber(String accountNumber);
}
