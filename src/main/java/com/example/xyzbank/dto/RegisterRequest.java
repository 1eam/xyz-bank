package com.example.xyzbank.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record RegisterRequest(
        @NotNull
        String oneTimePassword,
        @NotNull //TODO: ADD EXCEPTION HANDLER FOR EXISTING USERNAME
        String username,
        @NotNull
        String password,
        @NotNull
        MultipartFile governmentId,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull //TODO: VALIDATE AGE
        LocalDate dateOfBirth,
        @NotNull
        String street,
        @NotNull
        Integer houseNumber,
        @NotNull
        String postalCode,
        @NotNull
        String city,
        @NotNull //TODO: ADD INPUT VALIDATION FOR COUNTRY: ONLY ALLOWED INPUTS ARE "NL" & "BE"
        String country
) {}