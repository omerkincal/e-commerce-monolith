
package com.example.ecommercewebapp.domain.auth.permission.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PermissionService {
    Page<PermissionDto> getAll(Pageable pageable);

    PermissionDto getById(String permissionId);

    List<PermissionDto> getAllPermissions();
}

    