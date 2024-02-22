package com.example.ecommercewebapp.domain.auth.auth.web;


public record LoginRequest(
        String username,
        String password
) {
}
