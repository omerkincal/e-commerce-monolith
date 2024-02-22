
package com.example.ecommercewebapp.domain.auth.usergroup.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, String>, JpaSpecificationExecutor<UserGroup> {

    Optional<UserGroup> findByNameAndUserGroupType(String name, UserGroupType userGroupType);

    List<UserGroup> findAllByUserGroupType(UserGroupType userGroupType);
}
    