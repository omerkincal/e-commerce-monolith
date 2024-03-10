package com.example.ecommercewebapp.domain.auth.user.api;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {
    private String id;
    private Date created;
    private Date modified;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phoneNumber;
    private String extensionNumber;
    private Boolean status;
    private Boolean verified;
    private UserType userType;
}
