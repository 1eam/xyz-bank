package com.example.xyzbank.repository;

import com.example.xyzbank.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findByIso(String iso);
}
