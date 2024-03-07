package com.example.xyzbank.dto;

import com.example.xyzbank.annotation.AgeLimit;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record RegisterRequest(
        @NotNull
        String oneTimePassword,
        @NotNull @Size(min = 10, message = "Username should not be less than 10 characters")
        String username,
        @NotNull
        MultipartFile governmentId,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull @AgeLimit(minimumAge = 18, message = "Customer should be at least 18 years old in order to register")
        LocalDate dateOfBirth,
        @NotNull
        String street,
        @NotNull
        Integer houseNumber,
        @NotNull
        String postalCode,
        @NotNull
        String city,
        @NotNull @Pattern(regexp="(?i)NL|BE", message = "Only customers with a valid address in the Netherlands and Belgium can register at xyx-bank. Allowed values: 'NL','BE'")
        String country
) {}