
package com.example.ecommercewebapp.domain.auth.usergroup.web;

import com.example.ecommercewebapp.domain.auth.usergroup.impl.UserGroupType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserGroupRequest {
    @NotNull(message = "validation.required.name")
    @NotBlank(message = "validation.required.name")
    @NotEmpty(message = "validation.required.name")
    private String name;

    private Set<String> permissions;
    @NotNull(message = "validation.required.userGroupType")
    @NotBlank(message = "validation.required.userGroupType")
    @NotEmpty(message = "validation.required.userGroupType")
    private UserGroupType userGroupType;

    private String description;

}
    