package com.example.xyzbank.service;

import com.example.xyzbank.domain.AccountType;
import com.example.xyzbank.domain.Country;
import com.example.xyzbank.domain.Currency;
import com.example.xyzbank.domain.userdetails.Role;
import com.example.xyzbank.domain.userdetails.RoleEnum;
import com.example.xyzbank.repository.*;
import com.neovisionaries.i18n.CountryCode;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * This class is meant to be, and solely responsible for initializing constant data for the lookup tables.
 * The currently considered lookup tables are: role, account_type, currency, country and address*
 * *With an exception to address. Even though address is a lookup table for this module, its official data will not be initialized from within this module. This is because there are government-managed registries outside this module that contain all valid addresses. Also because not all existing address-data will be stored in the address table of this module, but solely the ones related to registered customers with a banking account. Keeping up-to-date of this address-data will be managed outside this module.
 */
@Component
public class DatabaseInitializer {

    public static final String ACCOUNTTYPE_PERSONAL = "PERSONAL";
    public static final String ACCOUNTTYPE_BUSINESS = "BUSINESS";
    public static final String CURRENCY_EUR = CountryCode.getByCode(CountryCode.NL.name()).getAlpha2().toUpperCase(); //EUR
    public static final String CURRENCY_USD = CountryCode.getByCode(CountryCode.US.name()).getAlpha2().toUpperCase(); //USD
    public static final String COUNTRY_NAME_NETHERLANDS = CountryCode.getByCode(CountryCode.NL.name()).getName(); //NETHERLANDS
    public static final String COUNTRY_NAME_BELGIUM = CountryCode.getByCode(CountryCode.BE.name()).getName(); //BELGIUM

    private RoleRepository roleRepository;
    private CurrencyRepository currencyRepository;
    private AccountTypeRepository accountTypeRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CountryRepository countryRepository;
    Logger LOG = LoggerFactory.getLogger(DatabaseInitializer.class);

    public DatabaseInitializer(RoleRepository roleRepository, CurrencyRepository currencyRepository, AccountTypeRepository accountTypeRepository, BankAccountRepository bankAccountRepository, CountryRepository countryRepository) {
        this.roleRepository = roleRepository;
        this.currencyRepository = currencyRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void initializeData() {
        LOG.info("Initializing data for tables Role, Currency, AccountType and Country");

        roleRepository.saveAndFlush(Role.createRole(RoleEnum.USER));

        currencyRepository.saveAll(Arrays.asList(
                new Currency(CURRENCY_EUR),
                new Currency(CURRENCY_USD))
        );

        accountTypeRepository.saveAll(Arrays.asList(
                new AccountType(ACCOUNTTYPE_PERSONAL),
                new AccountType(ACCOUNTTYPE_BUSINESS))
        );

        countryRepository.saveAll(Arrays.asList(
                new Country(CountryCode.NL.name(), COUNTRY_NAME_NETHERLANDS),
                new Country(CountryCode.BE.name(), COUNTRY_NAME_BELGIUM))
        );
    }
}
