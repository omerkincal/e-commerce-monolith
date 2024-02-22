
package com.example.ecommercewebapp.domain.auth.userusergroup.impl;

import com.example.ecommercewebapp.domain.auth.user.api.UserService;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupService;
import com.example.ecommercewebapp.domain.auth.userusergroup.api.UserUserGroupDto;
import org.springframework.util.StringUtils;

public class UserUserGroupMapper {

    private UserUserGroupMapper() {
    }
    public static UserUserGroupDto toDto(UserUserGroup userUserGroup, UserGroupService userGroupService) {
        UserGroupDto userGroup = StringUtils.hasLength(userUserGroup.getUserGroupId()) ? userGroupService.getById(userUserGroup.getUserGroupId()) : null;
        return UserUserGroupDto.builder()
                .id(userUserGroup.getId())
                .created(userUserGroup.getCreated())
                .modified(userUserGroup.getModified())
                .userId(userUserGroup.getUserId())
                .userGroup(userGroup)
                .build();
    }

    public static UserUserGroupDto toDto(UserUserGroup userUserGroup) {
        return UserUserGroupDto.builder()
                .id(userUserGroup.getId())
                .created(userUserGroup.getCreated())
                .modified(userUserGroup.getModified())
                .userId(userUserGroup.getUserId())
                .userGroup(UserGroupDto.builder()
                        .id(userUserGroup.getUserGroupId())
                        .build())
                .build();
    }

    public static UserUserGroup toEntity(UserUserGroup userUserGroup, UserUserGroupDto dto, UserGroupService userGroupService, UserService userService) {
        if(StringUtils.hasLength(dto.getUserId())) {
            userService.checkUserExists(userUserGroup.getUserId());
        }
        userUserGroup.setUserId(dto.getUserId());
        userUserGroup.setUserGroupId(dto.getUserGroup().getId() != null ? userGroupService.getById(dto.getUserGroup().getId()).getId() : null);
        return userUserGroup;
    }

    public static UserUserGroup toEntity(UserUserGroup userUserGroup, UserUserGroupDto dto) {
        userUserGroup.setUserId(dto.getUserId());
        userUserGroup.setUserGroupId(dto.getUserGroup().getId());
        return userUserGroup;
    }
}

    