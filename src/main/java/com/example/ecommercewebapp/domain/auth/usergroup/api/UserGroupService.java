
package com.example.ecommercewebapp.domain.auth.usergroup.api;

import com.example.ecommercewebapp.domain.auth.usergroup.impl.UserGroupType;
import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserGroupService {
    Page<UserGroupDto> getAll(Pageable pageable);

    UserGroupDto getById(String id);

    UserGroupDto save(UserGroupDto dto);

    UserGroupDto update(String id, UserGroupDto dto);

    void delete(String id);

    Page<UserGroupDto> filter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable);

    UserGroupDto findByNameAndUserGroupTypeDto(String name, UserGroupType userGroupType);

    Page<UserGroupDto> panelFilter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable);

    List<UserGroupDto> panelGetAll();
}

    