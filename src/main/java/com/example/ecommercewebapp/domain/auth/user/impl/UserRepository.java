package com.example.ecommercewebapp.domain.auth.user.impl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
