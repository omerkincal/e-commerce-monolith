package com.example.ecommercewebapp.domain.auth.user.impl;

import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserService;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(
                () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), username));
        return user;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto getById(String id) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto, String id) {
        return null;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
