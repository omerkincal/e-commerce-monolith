package com.example.ecommercewebapp.domain.auth.user.impl;

import com.example.ecommercewebapp.domain.auth.user.api.UserType;
import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = User.TABLE, uniqueConstraints = {
        @UniqueConstraint(columnNames = User.COL_EMAIL),
})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {
    public static final String TABLE = "users";
    private static final String COL_NAME = "name";
    private static final String COL_SURNAME = "surname";
    private static final String COL_PASSWORD = "password";
    public static final String COL_EMAIL = "email";
    public static final String COL_CHANGE_PASSWORD_CODE = "change_password_code";
    private static final String COL_STATUS = "status";
    private static final String COL_PHONE_NUMBER = "phone_number";
    private static final String COL_EXTENSION = "extension_number";
    private static final String COL_PROFILE_ID = "profile_id";
    private static final String COL_VERIFIED = "verified";
    private static final String COL_USER_TYPE = "user_type";
    private static final String COL_LAST_PASSWORDS = "last_passwords";
    private static final String COL_LAST_PASSWORD_CHANGE = "last_password_change";

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_CHANGE_PASSWORD_CODE)
    private String changePasswordCode;

    @Column(name = COL_SURNAME)
    private String surname;

    @Column(name = COL_PASSWORD)
    private String password;

    @Column(name = COL_EMAIL)
    private String email;

    @Column(name = COL_PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = COL_EXTENSION)
    private String extensionNumber;

    @Column(name = COL_STATUS)
    private Boolean status;

    @Column(name = COL_VERIFIED)
    private Boolean verified;

    @Enumerated(EnumType.STRING)
    @Column(name = COL_USER_TYPE)
    private UserType userType;
}
