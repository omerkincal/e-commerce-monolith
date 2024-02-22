
package com.example.ecommercewebapp.domain.auth.permission.api;

import com.example.ecommercewebapp.domain.auth.permission.web.PermissionRequest;
import com.example.ecommercewebapp.domain.auth.permission.web.PermissionResponse;
import com.example.ecommercewebapp.library.utils.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class PermissionMapper {

    private PermissionMapper() {
    }

    public static PermissionDto toDto(PermissionRequest permissionRequest) {
        return PermissionDto.builder()
                .name(permissionRequest.getName())
                .description(permissionRequest.getDescription())
                .displayName(permissionRequest.getDisplayName())
                .build();
    }

    public static PermissionResponse toResponse(PermissionDto permissionDto) {
        return PermissionResponse.builder()
                .id(permissionDto.getId())
                .created(permissionDto.getCreated())
                .modified(permissionDto.getModified())
                .displayName(permissionDto.getDisplayName())
                .name(permissionDto.getName())
                .description(permissionDto.getDescription())
                .type(permissionDto.getType())
                .build();
    }

    public static Page<PermissionResponse> toPageResponse(Page<PermissionDto> permissions) {
        return PageUtil.pageToDto(permissions, PermissionMapper::toResponse);
    }

    public static List<PermissionResponse> toResponse(List<PermissionDto> permissionDtos) {
        return permissionDtos.stream().map(PermissionMapper::toResponse).toList();
    }

    public static List<PermissionResponse> toDataResponse(List<PermissionDto> allPermissions) {
        return allPermissions.stream().map(PermissionMapper::toResponse).toList();
    }
}

    