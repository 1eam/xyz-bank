package com.example.xyzbank.repository;

import com.example.xyzbank.domain.userdetails.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
