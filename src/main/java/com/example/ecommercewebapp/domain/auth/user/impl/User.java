package com.example.ecommercewebapp.domain.auth.user.impl;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = User.Table)
public class User extends AbstractEntity {
    public static final String Table = "users";
    private static final String COL_NAME = "name";
    private static final String COL_SURNAME = "surname";
    private static final String COL_USERNAME = "username";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_ = "password";
    private static final String COLPASSWORD = "password";

}
