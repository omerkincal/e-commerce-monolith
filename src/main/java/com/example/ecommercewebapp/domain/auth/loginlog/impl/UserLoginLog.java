package com.example.ecommercewebapp.domain.auth.loginlog.impl;

import com.example.ecommercewebapp.domain.auth.user.api.UserType;
import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = UserLoginLog.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginLog extends AbstractEntity {
    public static final String TABLE = "user_login_log";
    private static final String COL_NAME = "name";
    private static final String COL_SURNAME = "surname";
    private static final String COL_EMAIL = "email";
    private static final String COL_IP_ADDRESS = "ip_address";
    private static final String COL_LOGIN_TIME = "login_time";
    private static final String COL_USER_TYPE = "user_type";

    @Column(name = COL_NAME)
    private String name;
    @Column(name = COL_SURNAME)
    private String surname;
    @Column(name = COL_EMAIL, nullable = false)
    private String email;
    @Column(name = COL_IP_ADDRESS, nullable = false)
    private String ipAddress;
    @Column(name = COL_LOGIN_TIME, nullable = false)
    private Date loginTime;
    @Enumerated(EnumType.STRING)
    @Column(name = COL_USER_TYPE)
    private UserType userType;

}
