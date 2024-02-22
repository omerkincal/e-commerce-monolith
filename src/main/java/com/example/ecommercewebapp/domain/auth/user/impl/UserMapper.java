package com.example.ecommercewebapp.domain.auth.user.impl;


import com.example.ecommercewebapp.domain.auth.auth.api.SignUpDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import com.example.ecommercewebapp.domain.auth.userusergroup.api.UserUserGroupDto;

import java.util.List;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .extensionNumber(user.getExtensionNumber())
                .status(user.getStatus())
                .userType(user.getUserType())
                .verified(user.getVerified())
                .build();
    }

    public static UserDto toDto(User user, List<UserUserGroupDto> userUserGroupDtos) {
        return UserDto.builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .extensionNumber(user.getExtensionNumber())
                .status(user.getStatus())
                .userType(user.getUserType())
                .verified(user.getVerified())
                .userGroups(userUserGroupDtos != null ? userUserGroupDtos.stream()
                        .filter(userUserGroupDto -> userUserGroupDto.getUserId() != null && userUserGroupDto.getUserId().equals(user.getId()))
                        .map(UserUserGroupDto::getUserGroup)
                        .toList() : null)
                .build();
    }

    public static User toEntity(SignUpDto signUpDto) {
        User user = new User();
        user.setPassword(signUpDto.password());
        user.setEmail(signUpDto.email());
        user.setName(signUpDto.name());
        user.setSurname(signUpDto.surname());
        user.setPhoneNumber(signUpDto.phoneNumber());
        user.setStatus(true);
        user.setUserType(signUpDto.userType());
        user.setVerified(false);
        user.setExtensionNumber(signUpDto.extensionNumber());
        return user;
    }

    public static User toEntity(User user, UserDto dto) {
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setExtensionNumber(dto.getExtensionNumber());
        user.setPassword(dto.getPassword());
        user.setStatus(dto.getStatus());
        user.setUserType(dto.getUserType());
        user.setVerified(dto.getVerified() != null && dto.getVerified());
        return user;
    }
}
