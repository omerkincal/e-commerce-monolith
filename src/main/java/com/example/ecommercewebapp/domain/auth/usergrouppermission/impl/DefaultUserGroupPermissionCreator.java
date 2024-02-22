package com.example.ecommercewebapp.domain.auth.usergrouppermission.impl;

import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.permission.impl.PermissionServiceImpl;
import com.example.ecommercewebapp.domain.auth.usergroup.api.DefaultUserGroupCreatedEvent;
import com.example.ecommercewebapp.domain.auth.usergroup.api.DefaultUserGroupCreatedForCustomerEvent;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.api.UserGroupPermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUserGroupPermissionCreator {

    private final UserGroupPermissionServiceImpl service;
    private final PermissionServiceImpl permissionService;

    @EventListener
    @Order(1)
    public void creatDefaultUserGroupPermissionForCustomer(DefaultUserGroupCreatedForCustomerEvent defaultUserGroupCreatedForCustomerEvent) {
        var permissionIds = permissionService.getAllIds();
        permissionIds.forEach(permissionId -> service.findByUserGroupIdAndPermissionId(defaultUserGroupCreatedForCustomerEvent.userGroupId(), defaultUserGroupCreatedForCustomerEvent.permissionId()).ifPresentOrElse(ugp -> {
                    //nothing
                },
                () ->
                        service.saveWithoutControl(UserGroupPermissionDto.builder()
                                .permission(PermissionDto.builder()
                                        .id(defaultUserGroupCreatedForCustomerEvent.permissionId())
                                        .build())
                                .userGroup(UserGroupDto.builder()
                                        .id(defaultUserGroupCreatedForCustomerEvent.userGroupId())
                                        .build())
                                .build())
        ));
    }


    @EventListener
    @Order(1)
    public void creatDefaultUserGroupPermission(DefaultUserGroupCreatedEvent defaultUserGroupCreatedEvent) {
        service.findByUserGroupIdAndPermissionId(defaultUserGroupCreatedEvent.userGroupId(), defaultUserGroupCreatedEvent.permissionId()).ifPresentOrElse(ugp -> {
            //nothing
        },
                () ->
                    service.saveWithoutControl(UserGroupPermissionDto.builder()
                                    .permission(PermissionDto.builder()
                                            .id(defaultUserGroupCreatedEvent.permissionId())
                                            .build())
                                    .userGroup(UserGroupDto.builder()
                                            .id(defaultUserGroupCreatedEvent.userGroupId())
                                            .build())
                            .build())
                );
    }
}




