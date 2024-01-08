package com.example.ecommercewebapp.domain.auth.user.web;

import com.example.ecommercewebapp.domain.auth.user.impl.UserType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponse {
    private String id;
    private Date created;
    private Date modified;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String extensionNumber;
    private Boolean verified;
    private UserType userType;
}