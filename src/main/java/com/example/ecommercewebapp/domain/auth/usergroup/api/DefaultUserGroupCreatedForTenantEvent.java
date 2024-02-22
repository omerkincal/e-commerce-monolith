package com.example.ecommercewebapp.domain.auth.usergroup.api;

public record DefaultUserGroupCreatedForTenantEvent(
        String userId,
        String userGroupId
) {
}
