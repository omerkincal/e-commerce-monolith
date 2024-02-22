package com.example.ecommercewebapp.domain.auth.user.impl;

import com.example.ecommercewebapp.domain.auth.user.api.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);
    Optional<User> findByChangePasswordCode(String changePasswordCode);
    Optional<User> findByEmailAndUserTypeAndStatusTrueAndVerifiedTrue(String email, UserType userType);

    Optional<User> findByEmailAndUserType(String email, UserType userType);

    @Query("SELECT COUNT(u) FROM User u WHERE u.userType = :user")
    Long countByUserType(@Param("user") UserType user);
}
