
package com.example.ecommercewebapp.domain.auth.usergrouppermission.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserGroupPermissionRepository extends JpaRepository<UserGroupPermission, String>, JpaSpecificationExecutor<UserGroupPermission> {

    List<UserGroupPermission> findAllByUserGroupIdIn(List<String> userGroupIds);

    Optional<UserGroupPermission> findByUserGroupIdAndPermissionId(String userGroupId, String permissionId);

    void deleteAllByUserGroupId(String userGroupId);
}
    