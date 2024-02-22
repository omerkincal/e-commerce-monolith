package com.example.ecommercewebapp.domain.auth.userusergroup.api;

import com.example.ecommercewebapp.domain.auth.usergroup.api.DefaultUserGroupCreatedEvent;
import com.example.ecommercewebapp.domain.auth.usergroup.api.DefaultUserGroupCreatedForTenantEvent;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.domain.auth.userusergroup.impl.UserUserGroupServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUserUserGroupCreator {

    private final UserUserGroupServiceImpl service;

    @EventListener
    @Order(2)
    public void creatDefaultUserUserGroup(DefaultUserGroupCreatedEvent defaultUserGroupCreatedEvent) {
        service.findByUserGroupIdAndUserId(defaultUserGroupCreatedEvent.userGroupId(), defaultUserGroupCreatedEvent.userId()).ifPresentOrElse(userGroup -> {
            //nothing
        },
                () ->
                    service.saveWithoutControl(UserUserGroupDto.builder()
                                    .userId(defaultUserGroupCreatedEvent.userId())
                                    .userGroup(UserGroupDto.builder()
                                            .id(defaultUserGroupCreatedEvent.userGroupId())
                                            .build())
                            .build())
                );
    }
    @EventListener
    @Order(2)
    public void creatDefaultUserUserGroupForTenant(DefaultUserGroupCreatedForTenantEvent defaultUserGroupCreatedEvent) {
        service.findByUserGroupIdAndUserId(defaultUserGroupCreatedEvent.userGroupId(), defaultUserGroupCreatedEvent.userId()).ifPresentOrElse(userGroup -> {
            //nothing
        },
                () ->
                    service.saveWithoutControl(UserUserGroupDto.builder()
                                    .userId(defaultUserGroupCreatedEvent.userId())
                                    .userGroup(UserGroupDto.builder()
                                            .id(defaultUserGroupCreatedEvent.userGroupId())
                                            .build())
                            .build())
                );
    }

}




