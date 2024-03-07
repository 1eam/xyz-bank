package com.example.xyzbank.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "account-type_name_unique", columnNames = "name")
        }
)
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public AccountType() {}

    public AccountType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}