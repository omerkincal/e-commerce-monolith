package com.example.ecommercewebapp.domain.auth.usergroup.api;

public record DefaultUserGroupCreatedForCustomerEvent(

        String permissionId,
        String userGroupId
) {
}
