package com.example.xyzbank.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "customer_government-id_unique", columnNames = "government_id")
        }
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String governmentId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Customer() {}

    private Customer(String governmentId, String firstName, String lastName, LocalDate dateOfBirth, Address address) {
        this.governmentId = governmentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public static Customer createCustomer(String governmentId, String firstName, String lastName, LocalDate dateOfBirth, Address address) {
        return new Customer(governmentId, firstName, lastName, dateOfBirth, address);
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(governmentId, customer.governmentId) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(dateOfBirth, customer.dateOfBirth) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, governmentId, firstName, lastName, dateOfBirth, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", governmentId='" + governmentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address +
                '}';
    }
}
