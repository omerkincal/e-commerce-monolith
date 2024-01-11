package com.example.ecommercewebapp.domain.auth.user.impl;

import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserService;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import com.example.ecommercewebapp.library.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserDto createUser(UserDto userDto) {
        repository.findByUsernameOrEmail(userDto.getUsername(), userDto.getEmail()).orElseThrow(
                () -> new CoreException(MessageCodes.ENTITY_ALREADY_EXISTS, User.class.getSimpleName(), userDto.getUsername()));
        return UserMapper.toDto(repository.save(UserMapper.toEntity(new User(),userDto, passwordEncoder)));
    }

    @Override
    public UserDto getById(String id) {
        return UserMapper.toDto(repository.findById(id).
                orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), id)));
    }

    @Override
    public UserDto update(UserDto userDto, String id) {
        return null;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(pageable), user -> UserMapper.toDto(user));
    }

    @Override
    public void delete(String id) {
        repository.findById(id).orElseThrow(
                () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), id));
    }

    @Override
    public User findByUsernameAndPasswordAndUserType(String username, String password, UserType userType) {
        System.out.println(passwordEncoder.encode(password));
        return repository.findByUsernameAndPasswordAndUserType(username, passwordEncoder.encode(password), userType)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), username));
    }
}
