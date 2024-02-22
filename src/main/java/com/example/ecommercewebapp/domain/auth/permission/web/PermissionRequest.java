
package com.example.ecommercewebapp.domain.auth.permission.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PermissionRequest {
    @NotNull(message = "validation.required.name")
    @NotBlank(message = "validation.required.name")
    @NotEmpty(message = "validation.required.name")
    private String name;
    @NotNull(message = "validation.required.displayName")
    @NotBlank(message = "validation.required.displayName")
    @NotEmpty(message = "validation.required.displayName")
    private String displayName;
    private String description;
}
    