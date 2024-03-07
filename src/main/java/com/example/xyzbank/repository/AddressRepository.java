package com.example.xyzbank.repository;

import com.example.xyzbank.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByStreetAndHouseNumberAndPostalCodeAndCityAndCountry_Iso(String street, Integer houseNumber, String postalCode, String city, String countryIso);
}
