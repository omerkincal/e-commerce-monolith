package com.example.ecommercewebapp.domain.auth.user.api;

import com.example.ecommercewebapp.domain.auth.user.impl.User;
import com.example.ecommercewebapp.domain.auth.user.web.UserRequest;
import com.example.ecommercewebapp.domain.auth.user.web.UserResponse;
import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import com.example.ecommercewebapp.domain.platform.product.api.ProductMapper;
import com.example.ecommercewebapp.domain.platform.product.web.ProductResponse;
import com.example.ecommercewebapp.library.utils.PageUtil;
import org.springframework.data.domain.Page;

public class UserMapper {

    public UserMapper(){

    }
    public static UserDto toDto(UserRequest request){
        return UserDto.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .name(request.getName())
                .surname(request.getSurname())
                .phoneNumber(request.getPhoneNumber())
                .extensionNumber(request.getExtensionNumber())
                .password(request.getPassword())
                .userType(request.getUserType())
                .build();
    }

    public static UserResponse toResponse(UserDto user){
        return UserResponse.builder()
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

    public static Page<UserResponse> toPageResponse(Page<UserDto> userList) {
        return PageUtil.pageToDto(userList, UserMapper::toResponse);
    }
}
