package com.example.ecommercewebapp.domain.auth.user.api;

import com.example.ecommercewebapp.library.datasourceloadoptions.DataSourceLoadOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    Page<UserDto> getAll(Pageable pageable);

    UserDto getById(String id);

    @Transactional
    UserDto save(UserDto dto);

    Long countByUserType(UserType userType);

    @Transactional
    UserDto update(String id, UserDto dto);

    @Transactional
    void delete(String id);

    Page<UserDto> filter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable);

    void checkUserExists(String userId);

    UserDto getMe();

    UserDto updateMe(UserDto dto);

    Page<UserDto> panelFilter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable);
}
