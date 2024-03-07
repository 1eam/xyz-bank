package com.example.xyzbank.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "currency_iso_unique", columnNames = "iso")
        }
)
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 3)
    private String iso;

    public Currency() {}

    public Currency(String iso) {
        this.iso = iso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) && Objects.equals(iso, currency.iso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iso);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", iso='" + iso + '\'' +
                '}';
    }
}