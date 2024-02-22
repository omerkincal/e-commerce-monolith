
package com.example.ecommercewebapp.domain.auth.userusergroup.api;

import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserUserGroupService {
    Page<UserUserGroupDto> getAll(Pageable pageable);

    UserUserGroupDto getById(String id);

    UserUserGroupDto save(UserUserGroupDto dto);

    UserUserGroupDto update(String id, UserUserGroupDto dto);

    void delete(String id);

    Page<UserUserGroupDto> filter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable);
}

    