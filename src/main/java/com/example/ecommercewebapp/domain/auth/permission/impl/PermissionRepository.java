
package com.example.ecommercewebapp.domain.auth.permission.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {

    Optional<Permission> findByName(String name);

    List<Permission> findAllByIdIn(List<String> permissionIds);

    List<Permission> findAllByType(PermissionType type);
}
    