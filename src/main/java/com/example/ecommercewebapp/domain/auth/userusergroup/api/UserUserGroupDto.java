package com.example.ecommercewebapp.domain.auth.userusergroup.api;

import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class UserUserGroupDto {
    private final String id;
    private final Date created;
    private final Date modified;
    private final String userId;
    private final UserGroupDto userGroup;
    private final Set<PermissionDto> permissions;
}
    