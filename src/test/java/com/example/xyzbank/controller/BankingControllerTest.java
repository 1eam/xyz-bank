package com.example.xyzbank.controller;

import com.example.xyzbank.repository.CountryRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankingControllerTest {

    private BankingController bankingController;
    private CountryRepository countryRepository;

    public BankingControllerTest(BankingController bankingController, CountryRepository countryRepository) {
        this.bankingController = bankingController;
        this.countryRepository = countryRepository;
    }

    //    TODO Mock multipartfile
//    @Test
//    void registerTest() {
//        ResponseEntity<RegisterResponse> response = bankingController.register(new RegisterRequest("OTP","username-test", "password", "govId, will be Multipartfile", "John", "Doe", LocalDate.EPOCH, "Banknamestreet", 7, "1234AB", "Bankcity", "NL"));
//        assertEquals(
//                "decide what to test: only http-statuses, or the actual body as well (already covered by service tests (password)",
//                response); //todo fix test, first parameter actual.
//    }
}