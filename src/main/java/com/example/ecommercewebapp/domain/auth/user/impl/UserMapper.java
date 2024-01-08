package com.example.ecommercewebapp.domain.auth.user.impl;

import com.example.ecommercewebapp.domain.auth.user.api.UserDto;

public class UserMapper {

    public UserMapper(){}

    public static UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .verified(user.getVerified())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .extensionNumber(user.getExtensionNumber())
                .password(user.getPassword())
                .userType(user.getUserType())
                .build();
    }

    public static User toEntity(User user, UserDto dto){
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setExtensionNumber(dto.getExtensionNumber());
        user.setUserType(dto.getUserType());
        return user;
    }
}
