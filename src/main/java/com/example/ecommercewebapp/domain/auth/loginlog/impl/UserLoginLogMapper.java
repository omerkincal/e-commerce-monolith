package com.example.ecommercewebapp.domain.auth.loginlog.impl;


import com.example.ecommercewebapp.domain.auth.loginlog.api.UserLoginLogDto;

import java.util.Date;

public class UserLoginLogMapper {
    private UserLoginLogMapper(){

    }
    public static UserLoginLogDto toDto(UserLoginLog userLoginLog){
        return UserLoginLogDto.builder()
                .name(userLoginLog.getName())
                .surname(userLoginLog.getSurname())
                .email(userLoginLog.getEmail())
                .ipAddress(userLoginLog.getIpAddress())
                .userType(userLoginLog.getUserType())
                .loginTime(userLoginLog.getLoginTime())
                .build();
    }
    public static UserLoginLog toEntity(UserLoginLog userLoginLog,UserLoginLogDto userLoginLogDto){
        userLoginLog.setName(userLoginLogDto.getName());
        userLoginLog.setSurname(userLoginLogDto.getSurname());
        userLoginLog.setEmail(userLoginLogDto.getEmail());
        userLoginLog.setIpAddress(userLoginLogDto.getIpAddress());
        userLoginLog.setUserType(userLoginLogDto.getUserType());
        userLoginLog.setLoginTime(new Date());
        return userLoginLog;
    }
}
