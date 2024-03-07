package com.example.xyzbank.domain.iban;

public enum BankCode {
    ABNA, INGB, RABO;

    public static BankCode getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
