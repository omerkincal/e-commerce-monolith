package com.example.ecommercewebapp.domain.auth.auth.web;

public record PasswordResetRequest(
        String newPassword,
        String changePasswordCode
) {
}
