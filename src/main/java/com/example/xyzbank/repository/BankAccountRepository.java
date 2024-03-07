package com.example.xyzbank.repository;

import com.example.xyzbank.domain.BankAccount;
import com.example.xyzbank.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
