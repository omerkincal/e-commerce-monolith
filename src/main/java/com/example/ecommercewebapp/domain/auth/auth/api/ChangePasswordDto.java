package com.example.ecommercewebapp.domain.auth.auth.api;


import com.example.ecommercewebapp.domain.auth.user.api.UserType;

public record ChangePasswordDto(
        String username,
        UserType userType
) {
}
