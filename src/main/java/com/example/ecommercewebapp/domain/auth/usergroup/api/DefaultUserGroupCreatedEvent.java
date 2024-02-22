package com.example.ecommercewebapp.domain.auth.usergroup.api;

public record DefaultUserGroupCreatedEvent(
        String userId,
        String permissionId,
        String userGroupId
) {
}
