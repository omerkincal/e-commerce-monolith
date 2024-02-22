package com.example.ecommercewebapp.domain.auth.user.impl;

import com.example.ecommercewebapp.domain.auth.permission.api.DefaultPermissionCreatedEvent;
import com.example.ecommercewebapp.domain.auth.user.api.DefaultUserCreatedEvent;
import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultUserCreator {
    public static final String SUPER_ADMIN_NAME = "super_admin";
    public static final String ADMIN_NAME = "admin";

    @Value("${management.super_admin.email}")
    public String SUPER_ADMIN_EMAIL;

    @Value("${management.super_admin.password}")
    public String SUPER_ADMIN_PASSWORD;

    @Value("${management.admin.email}")
    public String ADMIN_EMAIL;

    @Value("${management.admin.password}")
    public String ADMIN_PASSWORD;

    private final UserServiceImpl service;
    private final ApplicationEventPublisher publisher;
    @EventListener
    @Order(2)
    public void creatDefaultUser(DefaultPermissionCreatedEvent defaultPermissionCreatedEvent) {
        AtomicReference<UserDto> userDto = new AtomicReference<>();
        service.findByEmail(SUPER_ADMIN_EMAIL, UserType.SUPER_ADMIN).ifPresentOrElse(user ->
                        userDto.set(UserMapper.toDto(user))
                ,
                () ->
                        userDto.set(service.save(UserDto.builder()
                                .name(SUPER_ADMIN_NAME)
                                .email(SUPER_ADMIN_EMAIL)
                                .password(SUPER_ADMIN_PASSWORD)
                                .userType(UserType.SUPER_ADMIN)
                                .verified(true)
                                .status(true)
                                .build()))
        );
        publisher.publishEvent(new DefaultUserCreatedEvent(userDto.get().getId(), defaultPermissionCreatedEvent.permissionId()));

        service.findByEmail(ADMIN_EMAIL, UserType.ADMIN).ifPresentOrElse(user ->
                        userDto.set(UserMapper.toDto(user))
                ,
                () ->
                        userDto.set(service.save(UserDto.builder()
                                .name(ADMIN_NAME)
                                .email(ADMIN_EMAIL)
                                .password(ADMIN_PASSWORD)
                                .userType(UserType.ADMIN)
                                .verified(true)
                                .status(true)
                                .build()))
        );

        publisher.publishEvent(new DefaultUserCreatedEvent(userDto.get().getId(), defaultPermissionCreatedEvent.permissionId()));
    }


}




