package com.example.xyzbank.domain.iban;

public enum CountryCode {
    NL, BE;

    public static CountryCode getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
