
package com.example.ecommercewebapp.domain.auth.usergroup.impl;


import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.api.UserGroupPermissionDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserGroupMapper {

    private UserGroupMapper() {
    }

    public static UserGroupDto toDto(UserGroup userGroup, List<UserGroupPermissionDto> userGroupPermissions) {
        return UserGroupDto.builder()
                .id(userGroup.getId())
                .created(userGroup.getCreated())
                .modified(userGroup.getModified())
                .name(userGroup.getName())
                .userGroupType(userGroup.getUserGroupType())
                .description(userGroup.getDescription())
                .permissions(userGroupPermissions.stream()
                        .filter(userGroupPermission -> userGroupPermission.getUserGroup().getId().equals(userGroup.getId()))
                        .map(UserGroupPermissionDto::getPermission)
                        .collect(Collectors.toSet()))
                .build();
    }
    public static UserGroupDto toDto(UserGroup userGroup) {
        return UserGroupDto.builder()
                .id(userGroup.getId())
                .created(userGroup.getCreated())
                .modified(userGroup.getModified())
                .name(userGroup.getName())
                .userGroupType(userGroup.getUserGroupType())
                .description(userGroup.getDescription())
                .build();
    }

    public static UserGroupDto toDto(UserGroup userGroup, Set<PermissionDto> permissions) {
        return UserGroupDto.builder()
                .id(userGroup.getId())
                .created(userGroup.getCreated())
                .modified(userGroup.getModified())
                .name(userGroup.getName())
                .userGroupType(userGroup.getUserGroupType())
                .description(userGroup.getDescription())
                .permissions(permissions)
                .build();
    }

    public static UserGroup toEntity(UserGroup userGroup, UserGroupDto userGroupDto) {
        userGroup.setName(userGroupDto.getName());
        userGroup.setDescription(userGroupDto.getDescription());
        userGroup.setUserGroupType(userGroupDto.getUserGroupType());
        return userGroup;
    }
}

    