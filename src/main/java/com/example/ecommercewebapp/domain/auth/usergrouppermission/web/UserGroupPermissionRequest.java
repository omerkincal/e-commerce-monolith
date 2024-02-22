
package com.example.ecommercewebapp.domain.auth.usergrouppermission.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class UserGroupPermissionRequest {
    @NotNull(message = "validation.required.userGroup")
    @NotBlank(message = "validation.required.userGroup")
    @NotEmpty(message = "validation.required.userGroup")
    private final String userGroupId;
    @NotNull(message = "validation.required.permission")
    @NotBlank(message = "validation.required.permission")
    @NotEmpty(message = "validation.required.permission")
    private final String permissionId;
}
    