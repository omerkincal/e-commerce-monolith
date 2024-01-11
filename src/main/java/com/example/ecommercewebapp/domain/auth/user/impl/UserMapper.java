package com.example.ecommercewebapp.domain.auth.user.impl;

import com.example.ecommercewebapp.domain.auth.auth.api.SignUpDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public static User toEntity(User user, UserDto dto, BCryptPasswordEncoder passwordEncoder){
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setExtensionNumber(dto.getExtensionNumber());
        user.setUserType(dto.getUserType());
        return user;
    }

        public static UserDto toDto(SignUpDto signUpDto) {
            UserDto user = new UserDto();
            user.setUsername(signUpDto.username());
            user.setPassword(signUpDto.password());
            user.setEmail(signUpDto.email());
            user.setName(signUpDto.name());
            user.setSurname(signUpDto.surname());
            user.setPhoneNumber(signUpDto.phoneNumber());
            user.setUserType(signUpDto.userType());
            user.setVerified(false);
            user.setExtensionNumber(signUpDto.extensionNumber());
            return user;
        }
}
