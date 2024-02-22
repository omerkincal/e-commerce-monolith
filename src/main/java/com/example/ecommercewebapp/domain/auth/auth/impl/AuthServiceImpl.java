package com.example.ecommercewebapp.domain.auth.auth.impl;

import com.example.ecommercewebapp.domain.auth.auth.api.*;
import com.example.ecommercewebapp.domain.auth.loginlog.api.UserLoginLogDto;
import com.example.ecommercewebapp.domain.auth.loginlog.impl.UserLoginLogServiceImpl;
import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import com.example.ecommercewebapp.domain.auth.permission.impl.PermissionServiceImpl;
import com.example.ecommercewebapp.domain.auth.user.impl.User;
import com.example.ecommercewebapp.domain.auth.user.impl.UserMapper;
import com.example.ecommercewebapp.domain.auth.user.impl.UserServiceImpl;
import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.api.UserGroupPermissionDto;
import com.example.ecommercewebapp.domain.auth.usergrouppermission.impl.UserGroupPermissionServiceImpl;
import com.example.ecommercewebapp.domain.auth.userusergroup.api.UserUserGroupDto;
import com.example.ecommercewebapp.domain.auth.userusergroup.impl.UserUserGroupServiceImpl;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import com.example.ecommercewebapp.library.security.CustomUserDetails;
import com.example.ecommercewebapp.library.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserServiceImpl userService;
    private final UserUserGroupServiceImpl userUserGroupService;
    private final UserGroupPermissionServiceImpl userGroupPermissionService;
    private final PermissionServiceImpl permissionService;
    private final JwtUtils jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;
    private final UserLoginLogServiceImpl userLoginLogService;

    @Override
    public TokenDto login(LoginDto loginDto) {
        User user = userService.findByEmailAndPassword(loginDto.username(), loginDto.password(), loginDto.userType());

        List<UserUserGroupDto> userUserGroupDtoList = userUserGroupService.getAllByUserId(user.getId());
        List<UserGroupPermissionDto> userGroupPermissionDtoList = userGroupPermissionService.getAllByUserGroupIds(userUserGroupDtoList
                .stream()
                .map(UserUserGroupDto::getUserGroup)
                .map(UserGroupDto::getId)
                .toList());
        List<PermissionDto> permissionDtoList = permissionService.getAllByIds(userGroupPermissionDtoList
                .stream()
                .map(UserGroupPermissionDto::getPermission)
                .map(PermissionDto::getId)
                .toList());

        userLoginLogService.save(UserLoginLogDto.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .email(user.getEmail())
                        .ipAddress(getIpAddress())
                        .userType(user.getUserType())
                .build());

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(permissionDtoList.stream().map(PermissionDto::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toSet()))
                .build();
        String token = jwtUtil.generateToken(userDetails);
        return new TokenDto(token);
    }

    @Override
    @Transactional
    public TokenDto signUp(SignUpDto signUpDto){
        User savedUser = userService.save(UserMapper.toEntity(signUpDto));


        eventPublisher.publishEvent(new CreateUserEvent(savedUser.getId()));

        UserDetails userDetails = new CustomUserDetails(savedUser);


        String token = jwtUtil.generateToken(userDetails);



        return new TokenDto(token);
    }

    @Override
    @Transactional
    public String changePassword(ChangePasswordDto changePasswordDto) {
        User user = userService.findByEmail(changePasswordDto.username(), changePasswordDto.userType())
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, User.class.getSimpleName(), changePasswordDto.username()));
        UUID uuid = UUID.randomUUID();
        user.setChangePasswordCode(uuid.toString());
        userService.update(user);

        return uuid.toString();
    }

    @Override
    @Transactional
    public void resetPassword(PasswordResetDto passwordResetDto) {
        User user = userService.getByChangePasswordCode(passwordResetDto.changePasswordCode());
        String encodedPassword  = passwordEncoder.encode(passwordResetDto.newPassword());

        user.setPassword(encodedPassword);
        user.setChangePasswordCode(null);
        userService.update(user);
    }
    private String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getRemoteAddr();
    }
}
