package com.example.ecommercewebapp.domain.auth.user.api;

import com.example.ecommercewebapp.domain.auth.user.impl.User;
import com.example.ecommercewebapp.domain.auth.user.impl.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getById(String id);
    UserDto update(UserDto userDto, String id);
    Page<UserDto> getAll(Pageable pageable);
    //Page<UserDto> filter(DataSourceLoadOptions dataSourceLoadOptions, Pageable pageable);
    void delete(String id);
    User findByUsernameAndPasswordAndUserType(String username, String password, UserType userType);
}
