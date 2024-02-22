
package com.example.ecommercewebapp.domain.auth.userusergroup.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class UserUserGroupRequest {

    @NotNull(message = "validation.required.userGroup")
    @NotBlank(message = "validation.required.userGroup")
    @NotEmpty(message = "validation.required.userGroup")
    private final String userGroupId;
    @NotNull(message = "validation.required.user")
    @NotBlank(message = "validation.required.user")
    @NotEmpty(message = "validation.required.user")
    private final String userId;

    private final Set<String> permissions;
}
    