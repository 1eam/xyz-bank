package com.example.xyzbank.controller;

import com.example.xyzbank.dto.RegisterRequest;
import com.example.xyzbank.dto.RegisterResponse;
import com.example.xyzbank.service.BankingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/banking")
public class BankingController {

    private final BankingService bankingService;

    public BankingController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @PostMapping(value = "/register")

    public ResponseEntity<RegisterResponse> register(@Valid RegisterRequest registerRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bankingService.register(registerRequest));
    }
}
