
package com.example.ecommercewebapp.domain.auth.permission.web;

import com.example.ecommercewebapp.domain.auth.permission.impl.PermissionType;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class PermissionResponse {
    private final String id;
    private final Date created;
    private final Date modified;
    private final String name;
    private final String displayName;
    private final String description;
    private final PermissionType type;
}
    