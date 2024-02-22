
package com.example.ecommercewebapp.domain.auth.usergrouppermission.api;

import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class UserGroupPermissionDto {
    private final String id;
    private final Date created;
    private final Date modified;
    private final UserGroupDto userGroup;
    private final PermissionDto permission;
}
    