
package com.example.ecommercewebapp.domain.auth.usergrouppermission.api;

import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserGroupPermissionService {
    Page<UserGroupPermissionDto> getAll(Pageable pageable);

    UserGroupPermissionDto getById(String id);

    UserGroupPermissionDto save(UserGroupPermissionDto dto);

    UserGroupPermissionDto update(String id, UserGroupPermissionDto dto);

    void delete(String id);

    Page<UserGroupPermissionDto> filter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable);
}

    