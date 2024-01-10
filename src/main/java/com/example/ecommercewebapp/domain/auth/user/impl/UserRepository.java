package com.example.ecommercewebapp.domain.auth.user.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsernameAndPasswordAndUserType(String username, String encode, UserType userType);
}
