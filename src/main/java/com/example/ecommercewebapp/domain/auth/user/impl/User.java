package com.example.ecommercewebapp.domain.auth.user.impl;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = User.TABLE, uniqueConstraints = {
        @UniqueConstraint(columnNames = User.COL_EMAIL)
})
public class User extends AbstractEntity {
    public static final String TABLE = "usr";
    public static final String COL_EMAIL = "email";

    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD= "password";
    private static final String COL_NAME = "name";
    private static final String COL_SURNAME = "surname";
    private static final String COL_PHONE_NUMBER = "phone_number";
    private static final String COL_EXTENSION_NUMBER = "extension_number";
    private static final String COL_USER_TYPE = "user_type";
    private static final String COL_VERIFIED = "verified";

    @Column(name = COL_USERNAME)
    private String username;

    @Column(name = COL_PASSWORD)
    private String password;

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_SURNAME)
    private String surname;

    @Column(name = COL_EMAIL)
    private String email;

    @Column(name = COL_PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = COL_EXTENSION_NUMBER)
    private String extensionNumber;

    @Column(name = COL_VERIFIED)
    private Boolean verified;

    @Column(name = COL_USER_TYPE)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<GrantedAuthority> authorities;

}
