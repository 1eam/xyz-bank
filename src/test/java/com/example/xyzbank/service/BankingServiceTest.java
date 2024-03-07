package com.example.xyzbank.service;

import com.example.xyzbank.repository.AccountTypeRepository;
import com.example.xyzbank.repository.CountryRepository;
import com.example.xyzbank.repository.CurrencyRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankingServiceTest {

    private final BankingService bankingService;
    private CurrencyRepository currencyRepository;
    private AccountTypeRepository accountTypeRepository;
    private final CountryRepository countryRepository;

    public BankingServiceTest(BankingService bankingService, CurrencyRepository currencyRepository, AccountTypeRepository accountTypeRepository, CountryRepository countryRepository) {
        this.bankingService = bankingService;
        this.currencyRepository = currencyRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.countryRepository = countryRepository;
    }

    //    TODO mock multipartfile
//    @Test
//    void registerTest() {
//        RegisterResponse response = bankingService.register(new RegisterRequest("OTP","username-test", "password", "govId, will be Multipartfile", "John", "Doe", LocalDate.EPOCH, "Banknamestreet", 7, "1234AB", "Bankcity", "NL"));
//        assertEquals("password", response); //todo fix test, first parameter actual
//    }

}