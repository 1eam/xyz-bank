package com.example.xyzbank.repository;

import com.example.xyzbank.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    AccountType findByName(String name);
}
