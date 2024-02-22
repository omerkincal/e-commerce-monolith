
package com.example.ecommercewebapp.domain.auth.userusergroup.api;

import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.domain.auth.userusergroup.web.UserUserGroupRequest;
import com.example.ecommercewebapp.domain.auth.userusergroup.web.UserUserGroupResponse;
import com.example.ecommercewebapp.library.utils.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserUserGroupMapper {

    private UserUserGroupMapper() {
    }

    public static UserUserGroupDto toDto(UserUserGroupRequest userUserGroupRequest) {
        return UserUserGroupDto.builder()
                .userId(userUserGroupRequest.getUserId())
                .userGroup(UserGroupDto.builder().id(userUserGroupRequest.getUserGroupId()).build())
                .build();
    }

    public static UserUserGroupResponse toResponse(UserUserGroupDto userUserGroupDto) {
        return UserUserGroupResponse.builder()
                .id(userUserGroupDto.getId())
                .created(userUserGroupDto.getCreated())
                .modified(userUserGroupDto.getModified())
                .userId(userUserGroupDto.getUserId())
                .userGroup(userUserGroupDto.getUserGroup())
                .build();
    }

    public static Page<UserUserGroupResponse> toPageResponse(Page<UserUserGroupDto> userGroups) {
        return PageUtil.pageToDto(userGroups, UserUserGroupMapper::toResponse);
    }

    public static List<UserUserGroupResponse> toResponse(List<UserUserGroupDto> userUserGroupDtos) {
        return userUserGroupDtos.stream().map(UserUserGroupMapper::toResponse).toList();
    }
}

    