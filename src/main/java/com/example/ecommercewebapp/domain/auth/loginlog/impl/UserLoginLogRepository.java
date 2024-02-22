package com.example.ecommercewebapp.domain.auth.loginlog.impl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginLogRepository extends JpaRepository<UserLoginLog,String> {
}
