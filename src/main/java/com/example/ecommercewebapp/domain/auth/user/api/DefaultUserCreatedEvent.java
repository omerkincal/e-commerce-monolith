package com.example.ecommercewebapp.domain.auth.user.api;

public record DefaultUserCreatedEvent(
        String userId,
        String permissionId
) {
}
