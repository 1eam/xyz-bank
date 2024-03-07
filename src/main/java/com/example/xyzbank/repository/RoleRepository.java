package com.example.xyzbank.repository;

import com.example.xyzbank.domain.userdetails.Role;
import com.example.xyzbank.domain.userdetails.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByRole (RoleEnum roleEnum);
}
