package com.example.ecommercewebapp.domain.auth.auth.api;


import com.example.ecommercewebapp.domain.auth.user.api.UserType;

public record PasswordResetDto(
        String newPassword,
        String changePasswordCode,
        UserType userType
) {
}
