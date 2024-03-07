package com.example.xyzbank.repository;

import com.example.xyzbank.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country getByIso(String iso);
}
