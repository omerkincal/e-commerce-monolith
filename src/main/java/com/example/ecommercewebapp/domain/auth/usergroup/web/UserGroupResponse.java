
package com.example.ecommercewebapp.domain.auth.usergroup.web;

import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.usergroup.impl.UserGroupType;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class UserGroupResponse {
    private final String id;
    private final Date created;
    private final Date modified;
    private final String name;
    private final String description;
    private final UserGroupType userGroupType;
    private final Set<PermissionDto> permissions;
}
    