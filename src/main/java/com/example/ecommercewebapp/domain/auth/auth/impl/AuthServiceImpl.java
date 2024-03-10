package com.example.ecommercewebapp.domain.auth.auth.impl;

import com.example.ecommercewebapp.domain.auth.auth.api.*;
import com.example.ecommercewebapp.domain.auth.loginlog.impl.UserLoginLogServiceImpl;
import com.example.ecommercewebapp.domain.auth.user.impl.User;
import com.example.ecommercewebapp.domain.auth.user.impl.UserMapper;
import com.example.ecommercewebapp.domain.auth.user.impl.UserServiceImpl;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import com.example.ecommercewebapp.library.security.CustomUserDetails;
import com.example.ecommercewebapp.library.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserServiceImpl userService;
    private final JwtUtils jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;
    private final UserLoginLogServiceImpl userLoginLogService;

    @Override
    public TokenDto login(LoginDto loginDto) {
        User user = userService.findByEmailAndPassword(loginDto.username(), loginDto.password(), loginDto.userType());



        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
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
