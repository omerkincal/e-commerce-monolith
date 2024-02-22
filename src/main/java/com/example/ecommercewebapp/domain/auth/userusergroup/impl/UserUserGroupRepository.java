
package com.example.ecommercewebapp.domain.auth.userusergroup.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserUserGroupRepository extends JpaRepository<UserUserGroup, String>, JpaSpecificationExecutor<UserUserGroup> {

    List<UserUserGroup> findAllByUserId(String userId);
    Optional<UserUserGroup> findByUserGroupIdAndUserId(String userGroupId, String userId);

    List<UserUserGroup> findAllByUserIdIn(List<String> userIds);
}
    