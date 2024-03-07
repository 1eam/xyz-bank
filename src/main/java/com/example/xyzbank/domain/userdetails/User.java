package com.example.xyzbank.domain.userdetails;

import com.example.xyzbank.domain.Customer;
import jakarta.persistence.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_username_unique", columnNames = "username")
        }
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    private User(String username, Role role, Customer customer) {
        this.username = username;
        this.password = generatePassword();
        this.role = role;
        this.customer = customer;
    }

    public static User createUser(String username, Role role, Customer customer) {
        return new User(username, role, customer);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRole().name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(customer, user.customer) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, customer, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", customer=" + customer +
                ", role=" + role +
                '}';
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(16);
    }
}
