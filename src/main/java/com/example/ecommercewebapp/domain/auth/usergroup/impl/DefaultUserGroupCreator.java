package com.example.ecommercewebapp.domain.auth.usergroup.impl;

import com.example.ecommercewebapp.domain.auth.permission.api.DefaultPermissionCreatedForCustomerEvent;
import com.example.ecommercewebapp.domain.auth.user.api.DefaultUserCreatedEvent;
import com.example.ecommercewebapp.domain.auth.user.api.DefaultUserCreatedForTenantEvent;
import com.example.ecommercewebapp.domain.auth.usergroup.api.DefaultUserGroupCreatedEvent;
import com.example.ecommercewebapp.domain.auth.usergroup.api.DefaultUserGroupCreatedForCustomerEvent;
import com.example.ecommercewebapp.domain.auth.usergroup.api.DefaultUserGroupCreatedForTenantEvent;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class DefaultUserGroupCreator {

    public static final String SUPER_ADMIN_GROUP_NAME = "super_admin_group";
    public static final String ADMIN_GROUP_NAME = "admin_group";
    public static final String CUSTOMER_GROUP_NAME = "customer_group";
    private final UserGroupServiceImpl service;
    private final ApplicationEventPublisher publisher;

    @EventListener
    @Order(1)
    public void creatDefaultUserGroup(DefaultPermissionCreatedForCustomerEvent defaultPermissionCreatedForCustomerEvent) {
        AtomicReference<UserGroupDto> userGroupDto = new AtomicReference<>();
        service.findByNameAndUserGroupType(CUSTOMER_GROUP_NAME,UserGroupType.CUSTOMER).ifPresentOrElse(userGroup ->
                        userGroupDto.set(UserGroupMapper.toDto(userGroup))
                ,
                () ->
                        userGroupDto.set(service.save(UserGroupDto.builder()
                                .userGroupType(UserGroupType.CUSTOMER)
                                .description(CUSTOMER_GROUP_NAME)
                                .name(CUSTOMER_GROUP_NAME)
                                .build()))
        );

        publisher.publishEvent(new DefaultUserGroupCreatedForCustomerEvent(defaultPermissionCreatedForCustomerEvent.permissionId(), userGroupDto.get().getId()));
    }

    @EventListener
    @Order(1)
    public void creatDefaultUserGroup(DefaultUserCreatedEvent defaultUserCreatedEvent) {
        AtomicReference<UserGroupDto> userGroupDto = new AtomicReference<>();
        service.findByNameAndUserGroupType(SUPER_ADMIN_GROUP_NAME,UserGroupType.SUPER_ADMIN).ifPresentOrElse(userGroup ->
            userGroupDto.set(UserGroupMapper.toDto(userGroup))
        ,
                () ->
                    userGroupDto.set(service.save(UserGroupDto.builder()
                                    .userGroupType(UserGroupType.SUPER_ADMIN)
                                    .description(SUPER_ADMIN_GROUP_NAME)
                                    .name(SUPER_ADMIN_GROUP_NAME)
                            .build()))
                );

        publisher.publishEvent(new DefaultUserGroupCreatedEvent(defaultUserCreatedEvent.userId(),
                defaultUserCreatedEvent.permissionId(), userGroupDto.get().getId()));
    }
    @EventListener
    @Order(1)
    public void creatDefaultUserGroup(DefaultUserCreatedForTenantEvent defaultUserCreatedForTenantEvent) {
        AtomicReference<UserGroupDto> userGroupDto = new AtomicReference<>();
        service.findByNameAndUserGroupType(ADMIN_GROUP_NAME,UserGroupType.ADMIN).ifPresentOrElse(userGroup ->
                        userGroupDto.set(UserGroupMapper.toDto(userGroup))
                ,
                () ->
                        userGroupDto.set(service.save(UserGroupDto.builder()
                                .userGroupType(UserGroupType.ADMIN)
                                .description(ADMIN_GROUP_NAME)
                                .name(ADMIN_GROUP_NAME)
                                .build()))
        );

        publisher.publishEvent(new DefaultUserGroupCreatedForTenantEvent(defaultUserCreatedForTenantEvent.userId(),
                userGroupDto.get().getId()));
    }


}




