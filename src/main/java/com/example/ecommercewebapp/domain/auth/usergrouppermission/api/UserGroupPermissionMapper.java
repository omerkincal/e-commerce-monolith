
package com.example.ecommercewebapp.domain.auth.usergrouppermission.api;

import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.web.UserGroupPermissionRequest;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.web.UserGroupPermissionResponse;
import com.example.ecommercewebapp.library.utils.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserGroupPermissionMapper {

    private UserGroupPermissionMapper() {
    }

    public static UserGroupPermissionDto toDto(UserGroupPermissionRequest userGroupPermissionRequest) {
        return UserGroupPermissionDto.builder()
                .permission(PermissionDto.builder().id(userGroupPermissionRequest.getPermissionId()).build())
                .userGroup(UserGroupDto.builder().id(userGroupPermissionRequest.getUserGroupId()).build())
                .build();
    }

    public static UserGroupPermissionResponse toResponse(UserGroupPermissionDto userGroupPermissionDto) {
        return UserGroupPermissionResponse.builder()
                .id(userGroupPermissionDto.getId())
                .created(userGroupPermissionDto.getCreated())
                .modified(userGroupPermissionDto.getModified())
                .permission(userGroupPermissionDto.getPermission())
                .userGroup(userGroupPermissionDto.getUserGroup())
                .build();
    }

    public static Page<UserGroupPermissionResponse> toPageResponse(Page<UserGroupPermissionDto> userGroups) {
        return PageUtil.pageToDto(userGroups, UserGroupPermissionMapper::toResponse);
    }

    public static List<UserGroupPermissionResponse> toResponse(List<UserGroupPermissionDto> userGroupPermissionDtos) {
        return userGroupPermissionDtos.stream().map(UserGroupPermissionMapper::toResponse).toList();
    }
}

    