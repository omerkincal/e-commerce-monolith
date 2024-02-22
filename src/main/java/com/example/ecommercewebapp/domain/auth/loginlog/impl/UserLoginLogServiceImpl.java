package com.example.ecommercewebapp.domain.auth.loginlog.impl;

import com.example.ecommercewebapp.domain.auth.loginlog.api.UserLoginLogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserLoginLogServiceImpl {
    private final UserLoginLogRepository repository;

    @Async
    @Transactional
    public void save(UserLoginLogDto dto) {
        repository.save(UserLoginLogMapper.toEntity(new UserLoginLog(),dto));
    }
}
