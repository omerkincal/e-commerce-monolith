package com.example.ecommercewebapp.domain.auth.loginlog.api;

import com.example.ecommercewebapp.domain.auth.user.api.UserType;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class UserLoginLogDto {
    private final String name;
    private final String surname;
    private final String email;
    private final String ipAddress;
    private final Date loginTime;
    private final UserType userType;
}
