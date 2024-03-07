package com.example.xyzbank.dto;

public record OverviewResponse(String accountNumber, String accountType, String currency, Float balance) {}