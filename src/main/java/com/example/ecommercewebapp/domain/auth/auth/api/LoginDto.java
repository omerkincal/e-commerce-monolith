package com.example.ecommercewebapp.domain.auth.auth.api;


import com.example.ecommercewebapp.domain.auth.user.api.UserType;

public record LoginDto(
        String username,
        String password,
        UserType userType
) {
}
