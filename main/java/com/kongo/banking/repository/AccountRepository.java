package com.kongo.banking.repository;

import com.kongo.banking.models.Account;
import com.kongo.banking.service.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByIban(String iban);

    Optional<Account> findByUserId(Integer id);
}
