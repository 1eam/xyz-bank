package com.example.xyzbank.service;

import com.example.xyzbank.domain.Address;
import com.example.xyzbank.domain.BankAccount;
import com.example.xyzbank.domain.Customer;
import com.example.xyzbank.domain.userdetails.RoleEnum;
import com.example.xyzbank.domain.userdetails.User;
import com.example.xyzbank.dto.RegisterRequest;
import com.example.xyzbank.dto.RegisterResponse;
import com.example.xyzbank.exception.FileStorageException;
import com.example.xyzbank.repository.*;
import jakarta.transaction.Transactional;
import org.apache.tika.Tika;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.example.xyzbank.service.DatabaseInitializer.ACCOUNTTYPE_PERSONAL;
import static com.example.xyzbank.service.DatabaseInitializer.CURRENCY_EUR;

@Service
public class BankingService {

    private final BankAccountRepository bankAccountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CurrencyRepository currencyRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final CountryRepository countryRepository;

    @Value("${com.xyz-bank.upload-directory}")
    private String storageLocation;

    private final Tika TIKA = new Tika();

    public BankingService(BankAccountRepository bankAccountRepository, AccountTypeRepository accountTypeRepository, CurrencyRepository currencyRepository, RoleRepository roleRepository, CustomerRepository customerRepository, AddressRepository addressRepository, CountryRepository countryRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.currencyRepository = currencyRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest registerRequest) {

        //TODO: Autogenerate default password within user entity, (remove password from RegisterRequest)

        return new RegisterResponse(
                bankAccountRepository.save(BankAccount
                        .createBankAccount(accountTypeRepository.findByName(ACCOUNTTYPE_PERSONAL), currencyRepository.findByIso(CURRENCY_EUR), User
                                .createUser(registerRequest.username(), registerRequest.password(), roleRepository.getByRole(RoleEnum.USER), Customer
                                        .createCustomer(storeGovernmentIdFile(registerRequest), registerRequest.firstName(), registerRequest.lastName(), registerRequest.dateOfBirth(),
                                                addressRepository.findByStreetAndHouseNumberAndPostalCodeAndCityAndCountry_Iso(registerRequest.street(), registerRequest.houseNumber(), registerRequest.postalCode(), registerRequest.city(), registerRequest.country())
                                                .orElse(Address.createAddress(registerRequest.street(), registerRequest.houseNumber(), registerRequest.postalCode(), registerRequest.city(), countryRepository.getByIso(registerRequest.country())))
                                        )
                                )
                        )
                ).getUser().getPassword()); /**No need for mappers. Password is the only expected response.**/
    }

    private String storeGovernmentIdFile(RegisterRequest registerRequest) {
        Path rootLocation = Paths.get(storageLocation);
        File directory = new File(storageLocation);
        String contentType = null;
        String systemFilename = null;

        try {
            contentType = TIKA.detect(registerRequest.governmentId().getBytes());

            if (!(contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/png"))){
                throw new FileStorageException("Invalid file-type. Supported file-types for government-ID are: jpeg, jpg, png");
            }

            systemFilename =
                    "GOV-ID".concat("_")
                            .concat(registerRequest.firstName()).concat("_")
                            .concat(registerRequest.lastName()).concat("_")
                            .concat(String.valueOf(registerRequest.dateOfBirth())).concat("_")
                            .concat(registerRequest.username())
                            .concat(MimeTypes.getDefaultMimeTypes().forName(registerRequest.governmentId().getContentType()).getExtension())
                            .replace(" ", "_");

            Path destinationFile = rootLocation.resolve(Paths.get(systemFilename)).normalize().toAbsolutePath();

            if (directory.exists() == false){
                directory.mkdir();
            }

            Files.copy(registerRequest.governmentId().getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (MimeTypeException e) {
            throw new FileStorageException("Unrecognized mimetype", e);
        } catch (IOException e) {
            throw new FileStorageException("Failed to store file.", e);
        }

        return systemFilename;
    }

    /**
     * INFO: & TODO:
     * 1.
     * In order to prevent duplicate customers in the customer table, we need to check the social security number on the passport.
     * That part is currently left out.
     * Whereas in a case where we did have the SSN, we can prevent duplicate customer records.
     *
     * 2.
     * As of now the address table consists of several columns that the customer is free to enter.
     * Ideally these entered values should be validated against a government-managed datasource for Dutch and Belgian addresses e.g: BAG & CADGIS
     * and store their reference ID's instead of keeping track of columns like "street", "postal code" and "city" which change over time, and thus are unmanageable from within this module
     */
}

