
package com.example.ecommercewebapp.domain.auth.usergroup.api;

import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.usergroup.web.UserGroupRequest;
import com.example.ecommercewebapp.domain.auth.usergroup.web.UserGroupResponse;
import com.example.ecommercewebapp.library.utils.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class UserGroupMapper {

    private UserGroupMapper() {
    }

    public static UserGroupDto toDto(UserGroupRequest userGroupRequest) {
        return UserGroupDto.builder()
                .name(userGroupRequest.getName())
                .description(userGroupRequest.getDescription())
                .userGroupType(userGroupRequest.getUserGroupType())
                .permissions(userGroupRequest.getPermissions().stream()
                        .map(permission -> PermissionDto.builder()
                                .id(permission)
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    public static UserGroupResponse toResponse(UserGroupDto userGroupDto) {
        return UserGroupResponse.builder()
                .id(userGroupDto.getId())
                .created(userGroupDto.getCreated())
                .modified(userGroupDto.getModified())
                .name(userGroupDto.getName())
                .description(userGroupDto.getDescription())
                .userGroupType(userGroupDto.getUserGroupType())
                .permissions(userGroupDto.getPermissions())
                .build();
    }

    public static Page<UserGroupResponse> toPageResponse(Page<UserGroupDto> userGroups) {
        return PageUtil.pageToDto(userGroups, UserGroupMapper::toResponse);
    }

    public static List<UserGroupResponse> toResponse(List<UserGroupDto> userGroupDtos) {
        return userGroupDtos.stream().map(UserGroupMapper::toResponse).toList();
    }

    public static List<UserGroupResponse> toDataResponse(List<UserGroupDto> userGroupDtos) {
        return userGroupDtos.stream().map(UserGroupMapper::toResponse).toList();
    }
}

    