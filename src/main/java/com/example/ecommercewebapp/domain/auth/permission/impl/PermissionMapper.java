package com.example.ecommercewebapp.domain.auth.permission.impl;


import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;

public class PermissionMapper {

    private PermissionMapper() {
    }

    public static PermissionDto toDto(Permission permission) {
        return PermissionDto.builder()
                .id(permission.getId())
                .created(permission.getCreated())
                .modified(permission.getModified())
                .displayName(permission.getDisplayName())
                .name(permission.getName())
                .description(permission.getDescription())
                .type(permission.getType())
                .build();
    }

    public static Permission toEntity(Permission permission, PermissionDto permissionDto) {
        permission.setName(permissionDto.getName());
        permission.setDisplayName(permissionDto.getDisplayName());
        permission.setDescription(permissionDto.getDescription());
        permission.setType(permissionDto.getType());
        return permission;
    }
}

    