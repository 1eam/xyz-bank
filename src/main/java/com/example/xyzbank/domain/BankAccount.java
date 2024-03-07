package com.example.xyzbank.domain;

import com.example.xyzbank.domain.iban.BankCode;
import com.example.xyzbank.domain.iban.CountryCode;
import com.example.xyzbank.domain.userdetails.User;
import jakarta.persistence.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "bank-account_iban_unique", columnNames = "iban")
        }
)
@EntityListeners(AuditingEntityListener.class)
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 18)
    private String iban;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "account-type_id")
    private AccountType accountType;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "currency_id")
    private Currency currency;
    private Float balance;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "_user_id")
    private User user;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    public BankAccount() {
    }

    private BankAccount(AccountType accountType, Currency currency, User user) {
        this.balance = 0.0f;
        this.iban = generateIban();
        this.accountType = accountType;
        this.currency = currency;
        this.user = user;
    }

    public static BankAccount createBankAccount(AccountType accountType, Currency currency, User user) {
        return new BankAccount(accountType, currency, user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(id, that.id) && Objects.equals(iban, that.iban) && Objects.equals(accountType, that.accountType) && Objects.equals(currency, that.currency) && Objects.equals(balance, that.balance) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban, accountType, currency, balance, user);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", accountType=" + accountType +
                ", currency=" + currency +
                ", balance=" + balance +
                ", user=" + user +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }

    private String generateIban() {
        String countryCode = CountryCode.getRandom().name();
        String checksum = RandomStringUtils.randomNumeric(2);
        String bankCode = BankCode.getRandom().name();
        String accountNumber = RandomStringUtils.randomNumeric(10);

        return countryCode + checksum + bankCode + accountNumber;
    }

}
