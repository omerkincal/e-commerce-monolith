package com.example.ecommercewebapp.domain.auth.user.api;

import com.example.ecommercewebapp.domain.auth.user.web.UserRequest;
import com.example.ecommercewebapp.domain.auth.user.web.UserResponse;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.library.utils.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDto toDto(UserRequest request) {
        return UserDto.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .extensionNumber(request.getExtensionNumber())
                .status(request.getStatus())
                .userType(request.getUserType())
                .userGroups(request.getUserGroupIds() != null ? request.getUserGroupIds().stream().map( id -> UserGroupDto.builder().id(id).build()).toList() : null)
                .build();
    }

    public static UserResponse toResponse(UserDto dto) {
        return UserResponse.builder()
                .id(dto.getId())
                .created(dto.getCreated())
                .modified(dto.getModified())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .extensionNumber(dto.getExtensionNumber())
                .status(dto.getStatus())
                .userType(dto.getUserType())
                .userGroups(dto.getUserGroups())
                .verified(dto.getVerified())
                .build();
    }

    public static Page<UserResponse> toPageResponse(Page<UserDto> userDtos) {
        return PageUtil.pageToDto(userDtos, UserMapper::toResponse);
    }

    public static List<UserResponse> toResponse(List<UserDto> userDtos) {
        return userDtos.stream().map(UserMapper::toResponse).toList();
    }
}
