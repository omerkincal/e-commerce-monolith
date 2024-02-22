
package com.example.ecommercewebapp.domain.auth.usergrouppermission.impl;

import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.permission.api.PermissionService;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupService;
import com.example.ecommercewebapp.domain.auth.usergroup.impl.UserGroup;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.api.UserGroupPermissionDto;
import org.springframework.util.StringUtils;

public class UserGroupPermissionMapper {

    private UserGroupPermissionMapper() {
    }
    public static UserGroupPermissionDto toDto(UserGroupPermission userGroupPermission, PermissionService permissionService, UserGroupService userGroupService) {
        PermissionDto permission = StringUtils.hasLength(userGroupPermission.getPermissionId()) ? permissionService.getById(userGroupPermission.getPermissionId()) : null;
        UserGroupDto userGroup = StringUtils.hasLength(userGroupPermission.getUserGroupId()) ? userGroupService.getById(userGroupPermission.getUserGroupId()) : null;
        return UserGroupPermissionDto.builder()
                .id(userGroupPermission.getId())
                .created(userGroupPermission.getCreated())
                .modified(userGroupPermission.getModified())
                .permission(permission)
                .userGroup(userGroup)
                .build();
    }

    public static UserGroupPermissionDto toDto(UserGroupPermission userGroupPermission) {
        return UserGroupPermissionDto.builder()
                .id(userGroupPermission.getId())
                .created(userGroupPermission.getCreated())
                .modified(userGroupPermission.getModified())
                .permission(PermissionDto.builder()
                        .id(userGroupPermission.getPermissionId())
                        .build())
                .userGroup(UserGroupDto.builder()
                        .id(userGroupPermission.getUserGroupId())
                        .build())
                .build();
    }
    public static UserGroupPermission toEntity(UserGroupPermission userGroupPermission, UserGroupPermissionDto dto, PermissionService permissionService, UserGroupService userGroupService) {
        userGroupPermission.setPermissionId(dto.getPermission().getId() != null ? permissionService.getById(dto.getPermission().getId()).getId() : null);
        userGroupPermission.setUserGroupId(dto.getUserGroup().getId() != null ? userGroupService.getById(dto.getUserGroup().getId()).getId() : null);
        return userGroupPermission;
    }

    public static UserGroupPermission toEntity(UserGroupPermission userGroupPermission, UserGroup userGroup, PermissionDto permissionDto, PermissionService permissionService, UserGroupService userGroupService) {
        userGroupPermission.setPermissionId(permissionDto.getId() != null ? permissionService.getById(permissionDto.getId()).getId() : null);
        userGroupPermission.setUserGroupId(userGroup != null ? userGroup.getId() : null);
        return userGroupPermission;
    }

    public static UserGroupPermission toEntity(UserGroupPermission userGroupPermission, UserGroupPermissionDto dto) {
        userGroupPermission.setPermissionId(dto.getPermission().getId());
        userGroupPermission.setUserGroupId(dto.getUserGroup().getId());
        return userGroupPermission;
    }
}

    